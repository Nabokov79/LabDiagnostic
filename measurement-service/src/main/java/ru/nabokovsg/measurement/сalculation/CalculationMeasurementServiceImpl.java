package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationRepairMeasurementDto;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationMeasurementMapper;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationDefectMeasurementRepository;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationRepairMeasurementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationMeasurementServiceImpl implements CalculationMeasurementService {

    private final CalculationMeasurementMapper mapper;
    private final CalculationRepairMeasurementRepository calculationRepairRepository;
    private final CalculationDefectMeasurementRepository calculationDefectRepository;

    @Override
    public List<ResponseCalculationDefectMeasurementDto> getAllDefect(Long equipmentId) {
        return calculationDefectRepository.findAllByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseCalculationDefectMeasurement)
                .toList();
    }

    @Override
    public List<ResponseCalculationRepairMeasurementDto> getAllRepair(Long equipmentId) {
        return calculationRepairRepository.findAllByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseCalculationRepairMeasurement)
                .toList();
    }
}