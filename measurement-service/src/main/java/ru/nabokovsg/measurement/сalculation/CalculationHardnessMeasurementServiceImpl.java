package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationHardnessMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasurementStatus;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;
import ru.nabokovsg.measurement.repository.diagnostics.UltrasonicResidualThicknessMeasurementRepository;
import ru.nabokovsg.measurement.repository.library.AcceptableMetalHardnessRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CalculationHardnessMeasurementServiceImpl implements CalculationHardnessMeasurementService {

    private final AcceptableMetalHardnessRepository acceptableRepository;
    private final CalculationHardnessMeasurementMapper mapper;
    private final MeasurementClient client;
    private final UltrasonicResidualThicknessMeasurementRepository residualThicknessRepository;

    @Override
    public int getAverageMeasurementValue(Integer measurementValue, Integer measurementValueDto) {
        int[] measurements = {measurementValue, measurementValueDto};
        return Arrays.stream(measurements).sum() / measurements.length;
    }

    @Override
    public void setMeasurementStatus(HardnessMeasurement measurement) {
        EquipmentDto equipment = client.getEquipment(measurement.getElementId(), measurement.getPartElementId());
        AcceptableMetalHardness acceptableHardness = getAcceptableMetalHardness(equipment);
        validateMeasurement(equipment, measurement, acceptableHardness);
        if (acceptableHardness == null) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("NO_STANDARD").label
                                          , "NO_STANDARD");
            return;
        }
        if (compareMinAcceptable(measurement, acceptableHardness, true)
                                               && compareMaxAcceptable(measurement, acceptableHardness, true)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("ACCEPTABLE").label
                                          , "ACCEPTABLE");
            return;
        }
        if (compareMinAcceptable(measurement, acceptableHardness, false)
                                               || compareMaxAcceptable(measurement, acceptableHardness, false)) {
            mapper.mapWithMeasurementStatus(measurement
                                          , MeasurementStatus.valueOf("INVALID").label
                                          , "INVALID");
        }
    }

    private void validateMeasurement(EquipmentDto equipment
                                   , HardnessMeasurement measurement
                                   , AcceptableMetalHardness acceptableHardness) {
        UltrasonicResidualThicknessMeasurement residualThickness = getUltrasonicResidualThicknessMeasurement(measurement);
        if (measurement.getPartElementId() == null) {
            if ((equipment.getMinDiameter() <= acceptableHardness.getMinAcceptableDiameter())
                    || (residualThickness.getMinMeasurementValue() <= acceptableHardness.getMinAcceptableThickness())) {
                throw new BadRequestException(
                        String.format("The values of the element's standard size and residual thickness"
                                        + " are below acceptable values, standardSize=%s, residualThickness=%s"
                                , measurement.getStandardSize()
                                , residualThickness.getResidualThickness()));
            }
        }
    }

    private UltrasonicResidualThicknessMeasurement getUltrasonicResidualThicknessMeasurement(HardnessMeasurement measurement) {
        if (measurement.getPartElementId() != null) {
            return residualThicknessRepository.findByEquipmentIdAndElementIdAndPartElementIdAndMeasurementNumber(
                                                                                  measurement.getEquipmentId()
                                                                                , measurement.getElementId()
                                                                                , measurement.getPartElementId()
                                                                                , measurement.getMeasurementNumber());
        }
        return residualThicknessRepository.findByEquipmentIdAndElementIdAndMeasurementNumber(
                                                                                  measurement.getEquipmentId()
                                                                                , measurement.getElementId()
                                                                                , measurement.getMeasurementNumber());
    }

    private boolean compareMinAcceptable(HardnessMeasurement measurement
                                       , AcceptableMetalHardness acceptableHardness
                                       , boolean flag) {
        if (flag) {
            return measurement.getMeasurementValue() >= acceptableHardness.getMinAcceptableHardness();
        }
        return measurement.getMeasurementValue() < acceptableHardness.getMinAcceptableHardness();
    }

    private boolean compareMaxAcceptable(HardnessMeasurement measurement
                                       , AcceptableMetalHardness acceptableHardness
                                       , boolean flag) {
        if (flag) {
            return measurement.getMeasurementValue() <= acceptableHardness.getMaxAcceptableHardness();
        }
        return measurement.getMeasurementValue() > acceptableHardness.getMaxAcceptableHardness();
    }

    public AcceptableMetalHardness getAcceptableMetalHardness(EquipmentDto equipment) {
        if (equipment.getPartElementLibraryId() != null) {
            return acceptableRepository
                    .findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                                  equipment.getEquipmentLibraryId()
                                                                                , equipment.getElementLibraryId()
                                                                                , equipment.getPartElementLibraryId()
                                                                                , equipment.getStandardSize());
        }
        return acceptableRepository.findByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(
                                                                                      equipment.getEquipmentLibraryId()
                                                                                    , equipment.getElementLibraryId()
                                                                                    , equipment.getStandardSize());
    }
}