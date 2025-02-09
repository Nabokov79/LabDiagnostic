package ru.nabokovsg.measurement.сalculation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationMeasuredResidualThicknessMapper;
import ru.nabokovsg.measurement.model.diagnostics.MeasurementStatus;
import ru.nabokovsg.measurement.model.diagnostics.QDefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.QMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;
import ru.nabokovsg.measurement.repository.library.AcceptableResidualThicknessRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CalculationMeasuredResidualThicknessServiceImpl implements CalculationMeasuredResidualThicknessService {

    private final AcceptableResidualThicknessRepository repository;
    private final CalculationMeasuredResidualThicknessMapper mapper;
    private final EntityManager em;
    private final MeasurementClient client;

    @Override
    public void calculation(UltrasonicResidualThicknessMeasurement measurement) {
        EquipmentDto equipment = client.getEquipment(measurement.getElementId(), measurement.getPartElementId());
        AcceptableResidualThickness acceptableThickness = getAcceptableResidualThickness(equipment);
        Double maxCorrosion = getMaxCorrosionValue(measurement.getElementId(), measurement.getPartElementId());
        double residualThickness = countResidualThickness(measurement.getMinMeasurementValue()
                , maxCorrosion
                , acceptableThickness);
        double minAcceptableValue = countMinAcceptableValue(acceptableThickness, equipment);
        mapper.mapWithDataCalculation(measurement, equipment, maxCorrosion, residualThickness, minAcceptableValue);
        setMeasurementStatus(measurement, acceptableThickness);
    }

    public AcceptableResidualThickness getAcceptableResidualThickness(EquipmentDto equipment) {
        if (equipment.getPartElementLibraryId() != null) {
            return repository.findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                                    equipment.getEquipmentLibraryId()
                                                                                  , equipment.getElementLibraryId()
                                                                                  , equipment.getPartElementLibraryId()
                                                                                  , equipment.getStandardSize());
        } else {
            return repository.findByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(
                                                                                      equipment.getEquipmentLibraryId()
                                                                                    , equipment.getElementLibraryId()
                                                                                    , equipment.getStandardSize());
        }
    }

    private Double getMaxCorrosionValue(Long elementId, Long partElementId) {
        QDefectMeasurement defect = QDefectMeasurement.defectMeasurement;
        QMeasuredParameter parameter = QMeasuredParameter.measuredParameter;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(defect.elementId.eq(elementId));
        builder.and(defect.useCalculateThickness.eq(true));
        builder.and(parameter.defect.id.eq(defect.id));
        if (partElementId != null) {
            builder.and(defect.partElementId.eq(partElementId));
        }
        return new JPAQueryFactory(em).from(parameter)
                .select(parameter.value.max())
                .innerJoin(parameter.defect, defect)
                .where(builder)
                .fetchOne();
    }

    private double countResidualThickness(Double minMeasurementValue
                                        , Double maxCorrosion
                                        , AcceptableResidualThickness acceptableThickness) {
        double residualThickness = minMeasurementValue;
        if (maxCorrosion != null) {
            residualThickness = minMeasurementValue - maxCorrosion;
        }
        if (acceptableThickness != null && residualThickness
                        == (acceptableThickness.getAcceptableThickness() - acceptableThickness.getMeasurementError())) {
            return acceptableThickness.getAcceptableThickness();
        }
        if (residualThickness < 0) {
            return 0;
        }
        double scale = Math.pow(10, 1);
        return Math.ceil(residualThickness * scale) / scale;
    }

    private double countMinAcceptableValue(AcceptableResidualThickness acceptableThickness
                                         , EquipmentDto equipment) {
        if (acceptableThickness.getAcceptablePercent() != null) {
            return equipment.getThickness()
                    - (equipment.getThickness() * (double) (acceptableThickness.getAcceptablePercent() / 100));
        } else {
            return acceptableThickness.getAcceptableThickness();
        }
    }

    private void setMeasurementStatus(UltrasonicResidualThicknessMeasurement measurement
                                    , AcceptableResidualThickness acceptableThickness) {
        if (acceptableThickness == null) {
            mapper.mapWithMeasurementStatus(measurement
                    , MeasurementStatus.valueOf("NO_STANDARD").label
                    , "NO_STANDARD");
            return;
        }
        if (getAcceptable(measurement, acceptableThickness)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("ACCEPTABLE").label
                                          , "ACCEPTABLE");
            return;
        }
        if (getInvalid(measurement, acceptableThickness)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("INVALID").label
                                          , "INVALID");
            return;
        }
        if (getApproachingInvalid(measurement, acceptableThickness)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("APPROACHING_INVALID").label
                                          , "APPROACHING_INVALID");
            return;
        }
        if (getReachedInvalid(measurement, acceptableThickness)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("REACHED_INVALID").label
                                          , "REACHED_INVALID");
        }
    }

    private boolean getAcceptable(UltrasonicResidualThicknessMeasurement measurement
                                , AcceptableResidualThickness acceptableThickness) {
        return measurement.getResidualThickness()
                >= (acceptableThickness.getAcceptableThickness() + acceptableThickness.getMeasurementError());
    }

   private boolean getInvalid(UltrasonicResidualThicknessMeasurement measurement
                            , AcceptableResidualThickness acceptableThickness) {
        return (measurement.getResidualThickness() + acceptableThickness.getMeasurementError())
                                                                        < acceptableThickness.getAcceptableThickness();
    }

    private boolean getApproachingInvalid(UltrasonicResidualThicknessMeasurement measurement
                                        , AcceptableResidualThickness acceptableThickness) {
        return (measurement.getResidualThickness() > acceptableThickness.getAcceptableThickness())
                && (acceptableThickness.getAcceptableThickness() + acceptableThickness.getMeasurementError())
                                                                                > measurement.getResidualThickness();
    }

    private boolean getReachedInvalid(UltrasonicResidualThicknessMeasurement measurement
                                    , AcceptableResidualThickness acceptableThickness) {
        boolean reachedInvalid = Objects.equals(measurement.getResidualThickness()
                                              , acceptableThickness.getAcceptableThickness());
        if (!reachedInvalid) {
            reachedInvalid = (measurement.getResidualThickness() + acceptableThickness.getMeasurementError())
                                                                        == acceptableThickness.getAcceptableThickness();
        }
        return reachedInvalid;
    }
}