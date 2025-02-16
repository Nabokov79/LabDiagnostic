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
import java.util.Objects;

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
        MeasurementStatus measurementStatus =  MeasurementStatus.NO_STANDARD;
        if (acceptableHardness != null) {
            boolean compare = true;
            if (getAcceptable(measurement, acceptableHardness)) {
                measurementStatus = MeasurementStatus.ACCEPTABLE;
                compare = false;
            }
            if (compare &&  getInvalid(measurement, acceptableHardness)) {
                measurementStatus = MeasurementStatus.INVALID;
            }
        }
        mapper.mapWithMeasurementStatus(measurement, measurementStatus.label, String.valueOf(measurementStatus));
    }

    private void validateMeasurement(EquipmentDto equipment
                                   , HardnessMeasurement measurement
                                   , AcceptableMetalHardness acceptableHardness) {
        UltrasonicResidualThicknessMeasurement residualThickness = getUltrasonicResidualThicknessMeasurement(measurement);
        if (valid(equipment, acceptableHardness, residualThickness)) {
            throw new BadRequestException(
                    String.format("The values of the element's standard size and residual thickness"
                                    + " are below acceptable values, standardSize=%s, residualThickness=%s"
                            , measurement.getStandardSize()
                            , residualThickness.getResidualThickness()));
        }
    }

    private boolean valid(EquipmentDto equipment
                        , AcceptableMetalHardness acceptableHardness
                        , UltrasonicResidualThicknessMeasurement residualThickness) {
        boolean result = false;
        if (acceptableHardness.getMinAcceptableDiameter() != null) {
            Integer diameter = Objects.requireNonNullElse(equipment.getMinDiameter(), equipment.getMaxDiameter());
            result = diameter <= acceptableHardness.getMinAcceptableDiameter();
        }
        if (acceptableHardness.getMinAcceptableThickness() != null) {
            result = residualThickness.getMinMeasurementValue() <= acceptableHardness.getMinAcceptableThickness();
        }
        return result;
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

    private boolean getAcceptable(HardnessMeasurement measurement
            , AcceptableMetalHardness acceptableHardness) {
        return measurement.getMeasurementValue() >= acceptableHardness.getMinAcceptableHardness()
                && measurement.getMeasurementValue() <= acceptableHardness.getMaxAcceptableHardness();
    }

    private boolean getInvalid(HardnessMeasurement measurement
            , AcceptableMetalHardness acceptableHardness) {
        return measurement.getMeasurementValue() < acceptableHardness.getMinAcceptableHardness()
                || measurement.getMeasurementValue() > acceptableHardness.getMaxAcceptableHardness();
    }

    private AcceptableMetalHardness getAcceptableMetalHardness(EquipmentDto equipment) {
        if (equipment.getPartElementLibraryId() != null) {
            return acceptableRepository.findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                                  equipment.getEquipmentLibraryId()
                                                                                , equipment.getElementLibraryId()
                                                                                , equipment.getPartElementLibraryId());
        } else {
            return acceptableRepository.findByEquipmentLibraryIdAndElementLibraryId(equipment.getEquipmentLibraryId()
                                                                                    , equipment.getElementLibraryId());
        }
    }
}