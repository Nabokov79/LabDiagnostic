package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.ResponseElementHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.UpdateHardnessMeasurementDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.HardnessMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;
import ru.nabokovsg.measurement.repository.diagnostics.HardnessMeasurementRepository;
import ru.nabokovsg.measurement.сalculation.CalculationHardnessMeasurementService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HardnessMeasurementServiceImpl implements HardnessMeasurementService {

    private final HardnessMeasurementRepository repository;
    private final HardnessMeasurementMapper mapper;
    private final CalculationHardnessMeasurementService calculationService;

    @Override
    public ResponseElementHardnessMeasurementDto save(NewHardnessMeasurementDto measurementDto) {
        if (getDuplicate(measurementDto)) {
            throw new BadRequestException(String.format("Hardness measurement found: %s", measurementDto));
        }
        HardnessMeasurement measurement = mapper.mapToHardnessMeasurement(measurementDto);
        calculationService.setMeasurementStatus(measurement);
        return mapper.mapToResponseHardnessMeasurementDto(repository.save(measurement));
    }

    @Override
    public ResponseElementHardnessMeasurementDto update(UpdateHardnessMeasurementDto measurementDto) {
        if (repository.existsById(measurementDto.getId())) {
            HardnessMeasurement measurement = mapper.mapToUpdateHardnessMeasurement(measurementDto);
            calculationService.setMeasurementStatus(measurement);
            return mapper.mapToResponseHardnessMeasurementDto(repository.save(measurement));
        }
        throw new NotFoundException(
                String.format("Hardness measurement with id=%s not found update", measurementDto.getId()));
    }

    @Override
    public ResponseElementHardnessMeasurementDto get(Long id) {
        return mapper.mapToResponseHardnessMeasurementDto(
                repository.findById(id).orElseThrow(() ->
                        new NotFoundException(
                                String.format("Hardness measurement result with id=%s not found", id)))
        );
    }

    @Override
    public List<ResponseElementHardnessMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<HardnessMeasurement> measurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    measurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                            , elementId
                            , partElementId));
                }
                measurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            measurements.addAll(repository.findAllByEquipmentId(equipmentId));
        }
        return measurements.stream()
                           .map(mapper::mapToResponseHardnessMeasurementDto)
                           .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Hardness measurement with id=%s not found delete", id));
    }

    private boolean getDuplicate(NewHardnessMeasurementDto measurementDto) {
        if (measurementDto.getPartElementId() != null) {
            return repository.existsByEquipmentIdAndElementIdAndPartElementIdAndMeasurementNumber(
                                                                                  measurementDto.getEquipmentId()
                                                                                , measurementDto.getElementId()
                                                                                , measurementDto.getPartElementId()
                                                                                , measurementDto.getMeasurementNumber()
            );
        }
        return repository.existsByEquipmentIdAndElementIdAndMeasurementNumber(measurementDto.getEquipmentId()
                                                                          , measurementDto.getElementId()
                                                                          , measurementDto.getMeasurementNumber());
    }
}