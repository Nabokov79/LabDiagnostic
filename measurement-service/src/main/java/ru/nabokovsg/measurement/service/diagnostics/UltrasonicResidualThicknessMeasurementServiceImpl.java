package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.ResponseUltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.UltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.UltrasonicResidualThicknessMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;
import ru.nabokovsg.measurement.repository.diagnostics.UltrasonicResidualThicknessMeasurementRepository;
import ru.nabokovsg.measurement.сalculation.CalculationMeasuredResidualThicknessService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UltrasonicResidualThicknessMeasurementServiceImpl implements UltrasonicResidualThicknessMeasurementService {

    private final UltrasonicResidualThicknessMeasurementRepository repository;
    private final UltrasonicResidualThicknessMeasurementMapper mapper;
    private final CalculationMeasuredResidualThicknessService calculationService;

    @Override
    public ResponseUltrasonicResidualThicknessMeasurementDto save(UltrasonicResidualThicknessMeasurementDto measurementDto) {
        UltrasonicResidualThicknessMeasurement measurement = getByDuplicate(measurementDto);
        if (measurement == null) {
            measurement = mapper.mapToUltrasonicResidualThicknessMeasurement(measurementDto, LocalDate.now());
        } else {
            update(measurement, measurementDto);
        }
        calculationService.calculation(measurement);
        return mapper.mapToResponseUltrasonicThicknessMeasurementDto(repository.save(measurement));
    }

    private void update(UltrasonicResidualThicknessMeasurement measurement
            , UltrasonicResidualThicknessMeasurementDto measurementDto) {
        LocalDate date = LocalDate.now();
        if (measurement.getMeasurementDate().equals(date)) {
            measurement.setMinMeasurementValue(Math.min(measurement.getMinMeasurementValue()
                    , measurementDto.getMinMeasurementValue()));
            measurement.setMaxMeasurementValue(Math.max(measurement.getMaxMeasurementValue()
                    , measurementDto.getMaxMeasurementValue()));
        } else {
            mapper.mapToUpdateUltrasonicResidualThicknessMeasurement(measurement, measurementDto, date);
        }
    }

    @Override
    public ResponseUltrasonicResidualThicknessMeasurementDto get(Long id) {
        return mapper.mapToResponseUltrasonicThicknessMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseUltrasonicResidualThicknessMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<UltrasonicResidualThicknessMeasurement> measurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    measurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                            , elementId
                            , partElementId));
                }
                measurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            measurements.addAll(repository.findAllByEquipmentIdOrderByMeasurementNumberDesc(equipmentId));
        }
        return measurements.stream()
                           .map(mapper::mapToResponseUltrasonicThicknessMeasurementDto)
                           .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                String.format("Ultrasonic residual thickness measurement with id=%s not found for delete", id));
    }

    private UltrasonicResidualThicknessMeasurement getByDuplicate(UltrasonicResidualThicknessMeasurementDto measurementDto) {
        if (measurementDto.getPartElementId() != null) {
            return repository.findByEquipmentIdAndElementIdAndPartElementIdAndMeasurementNumber(
                                                                                  measurementDto.getEquipmentId()
                                                                                , measurementDto.getElementId()
                                                                                , measurementDto.getPartElementId()
                                                                                , measurementDto.getMeasurementNumber()
            );
        }
        return repository.findByEquipmentIdAndElementIdAndMeasurementNumber(measurementDto.getEquipmentId()
                                                                          , measurementDto.getElementId()
                                                                          , measurementDto.getMeasurementNumber());
    }

    public UltrasonicResidualThicknessMeasurement getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Ultrasonic residual thickness measurement result with id=%s not found", id))
        );
    }
}