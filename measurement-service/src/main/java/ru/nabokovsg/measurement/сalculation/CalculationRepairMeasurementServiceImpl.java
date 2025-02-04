package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationRepairMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.*;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationRepairMeasurementRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationRepairMeasurementServiceImpl implements CalculationRepairMeasurementService {

    private final CalculationRepairMeasurementRepository repository;
    private final CalculationRepairMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;

    @Override
    public void saveCalculationMinMax(RepairMeasurement repair, Set<RepairMeasurement> repairs) {
        Set<MeasuredParameter> parameters = repairs.stream()
                .map(RepairMeasurement::getMeasuredParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        CalculationRepairMeasurement calculationRepair = get(repair);
        String parametersString = calculationParameterService.calculateMeasuredParameters(parameters
                , repair.getCalculation());
        if (calculationRepair == null) {
            calculationRepair = mapper.mapToCalculationRepairMeasurement(repair, parametersString);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationRepair, parametersString);
        }
        repository.save(calculationRepair);
    }

    @Override
    public void saveWithoutCalculation(RepairMeasurement repair) {
        CalculationRepairMeasurement calculationRepair = repository.findByRepairId(repair.getId());
        if (calculationRepair == null) {
            calculationRepair = mapper.mapToCalculationRepairMeasurement(repair, repair.getParametersString());
        } else {
            mapper.mapToUpdateCalculationRepairMeasurement(calculationRepair, repair);
        }
        repository.save(calculationRepair);
    }

    private CalculationRepairMeasurement get(RepairMeasurement repair) {
        if (repair.getPartElementId() != null) {
            return repository.findByEquipmentIdAndElementIdAndPartElementIdAndRepairName(repair.getEquipmentId()
                    , repair.getElementId()
                    , repair.getPartElementId()
                    , repair.getRepairName());
        }
        return repository.findByEquipmentIdAndElementIdAndRepairName(repair.getEquipmentId()
                , repair.getElementId()
                , repair.getRepairName());
    }

    @Override
    public void delete(RepairMeasurement repair) {
        if (repair.getPartElementId() != null) {
            repository.deleteByEquipmentIdAndElementIdAndPartElementIdAndRepairName(repair.getEquipmentId()
                    , repair.getElementId()
                    , repair.getPartElementId()
                    , repair.getRepairName());
        } else {
            repository.deleteByEquipmentIdAndElementIdAndRepairName(repair.getEquipmentId()
                    , repair.getElementId()
                    , repair.getRepairName());
        }
    }

    @Override
    public void deleteByDefectId(RepairMeasurement repair) {
        repository.deleteByRepairId(repair.getId());
    }
}