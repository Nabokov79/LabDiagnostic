package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationRepairMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.*;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationRepairMeasurementRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationRepairMeasurementServiceImpl implements CalculationRepairMeasurementService {

    private final CalculationRepairMeasurementRepository repository;
    private final CalculationRepairMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;

    @Override
    public void save(RepairMeasurement repair, Set<RepairMeasurement> repairs, ParameterCalculationType type) {
        repairs.add(repair);
        calculation(repair, repairs, type);

    }

    @Override
    public void delete(RepairMeasurement repair, Set<RepairMeasurement> repairs, ParameterCalculationType type) {

    }

    private void calculation(RepairMeasurement repair, Set<RepairMeasurement> repairs, ParameterCalculationType type) {
        switch (type) {
            case MIN, MAX, MAX_MIN ->  saveOne(repair, repairs, type);
            case NO_ACTION -> saveAll(repair, repairs, type);
        }
    }

    private void saveOne(RepairMeasurement repair
                       , Set<RepairMeasurement> repairs
                       , ParameterCalculationType type) {
        Set<MeasuredParameter> measuredParameters = repairs.stream()
                .map(RepairMeasurement::getMeasuredParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        CalculationRepairMeasurement calculationDefect = get(repair);
        String parameters = calculationParameterService.getMeasuredParameters(measuredParameters, type);
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationRepairMeasurement(repair, parameters);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationDefect, parameters);
        }
        repository.save(calculationDefect);
    }

    private void saveAll(RepairMeasurement repair
                       , Set<RepairMeasurement> repairs
                       , ParameterCalculationType type) {
        Set<CalculationRepairMeasurement> repairsDb = getAll(repair);
        List<CalculationRepairMeasurement> calculationRepair = repairs
                .stream()
                .map(defectMeasurement ->  mapper.mapToCalculationRepairMeasurement(repair
                        , calculationParameterService.getMeasuredParameters(
                                defectMeasurement.getMeasuredParameters(), type)))
                .toList();
        if (!repairsDb.isEmpty()) {
            updateAll(repairsDb, calculationRepair);
            repository.saveAll(repairsDb);
            return;
        }
        repository.saveAll(calculationRepair);
    }

    private void updateAll(Set<CalculationRepairMeasurement> repairsDb, List<CalculationRepairMeasurement> repairs) {
        int index = 0;
        for (CalculationRepairMeasurement repair : repairsDb) {
            mapper.mapToUpdateCalculationRepairMeasurement(repairs.get(index), repair);
            index++;
        }
        repository.saveAll(repairsDb);
    }

    private Set<CalculationRepairMeasurement> getAll(RepairMeasurement repair) {
        if (repair.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndRepairName(repair.getEquipmentId()
                    , repair.getElementId()
                    , repair.getPartElementId()
                    , repair.getRepairName());
        }
        return repository.findAllByEquipmentIdAndElementIdAndRepairName(repair.getEquipmentId()
                , repair.getElementId()
                , repair.getRepairName());
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
}