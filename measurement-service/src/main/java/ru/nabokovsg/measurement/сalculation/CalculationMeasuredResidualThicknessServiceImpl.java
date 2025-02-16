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
        Double minAcceptableValue = null;
        double lowerLimit = 0.0;
        if (acceptableThickness != null) {
            minAcceptableValue = countMinAcceptableValue(acceptableThickness, equipment);
            lowerLimit = getLowerLimit(minAcceptableValue, acceptableThickness.getMeasurementError());
        }
        double measurementValue = getMeasurementValue(measurement.getMinMeasurementValue(), lowerLimit, minAcceptableValue);
        double maxCorrosion = getMaxCorrosionValue(measurement.getElementId(), measurement.getPartElementId());
        double residualThickness = countResidualThickness(measurementValue, maxCorrosion, acceptableThickness, minAcceptableValue);
        mapper.mapWithDataCalculation(measurement, equipment, maxCorrosion, residualThickness, minAcceptableValue);
        setMeasurementStatus(measurement, acceptableThickness);
    }

    private double countMinAcceptableValue(AcceptableResidualThickness acceptableThickness, EquipmentDto equipment) {
        if (acceptableThickness.getAcceptablePercent() != null) {
            double acceptableResidualThickness = equipment.getThickness() * ((double) acceptableThickness.getAcceptablePercent() / 100.0);
            return equipment.getThickness() - acceptableResidualThickness;
        } else {
            return acceptableThickness.getAcceptableThickness();
        }
    }

    private double getLowerLimit(Double minAcceptableValue, Float measurementError) {
        double lowerLimit = minAcceptableValue - measurementError;
        double scale = Math.pow(10, 1);
        return Math.ceil(lowerLimit * scale) / scale;
    }

    private double getMeasurementValue(Double minMeasurementValue, double lowerLimit, Double minAcceptableValue) {
        if (minAcceptableValue != null && lowerLimit < minMeasurementValue && minMeasurementValue < minAcceptableValue) {
            return minAcceptableValue;
        } else {
            return minMeasurementValue;
        }
    }

    private AcceptableResidualThickness getAcceptableResidualThickness(EquipmentDto equipment) {
        if (equipment.getPartElementLibraryId() != null) {
            return repository.findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                                  equipment.getEquipmentLibraryId()
                                                                                , equipment.getElementLibraryId()
                                                                                , equipment.getPartElementLibraryId());
        } else {
            return repository.findByEquipmentLibraryIdAndElementLibraryId(equipment.getEquipmentLibraryId()
                                                                        , equipment.getElementLibraryId());
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

    private double countResidualThickness(double measurementValue
                                        , Double maxCorrosion
                                        , AcceptableResidualThickness acceptableThickness
                                        , Double minAcceptableValue) {
        double residualThickness = measurementValue;
        if (maxCorrosion != null) {
            residualThickness = measurementValue - maxCorrosion;
        }
        if (minAcceptableValue != null && (residualThickness == (minAcceptableValue - acceptableThickness.getMeasurementError()))) {
            return minAcceptableValue;
        }
        double scale = Math.pow(10, 1);
        return Math.ceil(residualThickness * scale) / scale;
    }

    private void setMeasurementStatus(UltrasonicResidualThicknessMeasurement measurement
                                    , AcceptableResidualThickness acceptableThickness) {
        MeasurementStatus measurementStatus = MeasurementStatus.NO_STANDARD;
        if (acceptableThickness != null) {
            boolean compare = true;
            if (getAcceptable(measurement, acceptableThickness.getMeasurementError())) {
                measurementStatus = MeasurementStatus.ACCEPTABLE;
                compare = false;
            }
            if (compare && getInvalid(measurement)) {
                measurementStatus = MeasurementStatus.INVALID;
                compare = false;
            }
            if (compare && getApproachingInvalid(measurement, acceptableThickness.getMeasurementError())) {
                measurementStatus = MeasurementStatus.APPROACHING_INVALID;
                compare = false;
            }
            if (compare && getReachedInvalid(measurement, acceptableThickness.getMeasurementError())) {
                measurementStatus = MeasurementStatus.REACHED_INVALID;
            }
        }
        mapper.mapWithMeasurementStatus(measurement, measurementStatus.label, String.valueOf(measurementStatus));
    }

    private boolean getAcceptable(UltrasonicResidualThicknessMeasurement measurement, Float measurementError) {
        return measurement.getResidualThickness() >= (measurement.getMinAcceptableValue() + measurementError);
    }

   private boolean getInvalid(UltrasonicResidualThicknessMeasurement measurement) {
        return measurement.getResidualThickness() < measurement.getMinAcceptableValue();
    }

    private boolean getApproachingInvalid(UltrasonicResidualThicknessMeasurement measurement, Float measurementError) {
        return (measurement.getResidualThickness() > measurement.getMinAcceptableValue()) &&
                (measurement.getMinAcceptableValue() + measurementError) > measurement.getResidualThickness();
    }

    private boolean getReachedInvalid(UltrasonicResidualThicknessMeasurement measurement, Float measurementError) {
        boolean reachedInvalid = Objects.equals(measurement.getResidualThickness(), measurement.getMinAcceptableValue());
        if (!reachedInvalid) {
            reachedInvalid = (measurement.getResidualThickness() + measurementError)
                                                                        == measurement.getMinMeasurementValue();
        }
        return reachedInvalid;
    }
}