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
    public void factory(RepairMeasurement repair
                      , Set<RepairMeasurement> repairMeasurements
                      , ParameterCalculationType calculation) {
        switch (calculation) {
            case MIN, MAX, MAX_MIN -> {
                Set<MeasuredParameter> measuredParameters = repairMeasurements.stream()
                        .map(RepairMeasurement::getMeasuredParameters)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet());
                saveOne(repair, measuredParameters, calculation);
            }
            case NO_ACTION -> saveAll(repair, repairMeasurements, calculation);
        }
    }

    private void saveOne(RepairMeasurement repair
                       , Set<MeasuredParameter> measuredParameters
                       , ParameterCalculationType calculation) {
        CalculationRepairMeasurement calculationRepair = get(repair);
        String parameters = calculationParameterService.getMeasuredParameters(measuredParameters, calculation);
        if (calculationRepair == null) {
            repository.save(mapper.mapToCalculationRepairMeasurement(repair, parameters));
            return;
        }
        repository.save(mapper.mapToUpdateMeasuredParameters(calculationRepair, parameters));
    }

    private void saveAll(RepairMeasurement repair
                       , Set<RepairMeasurement> repairMeasurements
                       , ParameterCalculationType calculation) {
        Set<CalculationRepairMeasurement> repairsDb = getAll(repair);
        List<CalculationRepairMeasurement> repairs = repairMeasurements
                .stream()
                .map(defectMeasurement ->  mapper.mapToCalculationRepairMeasurement(repair
                        , calculationParameterService.getMeasuredParameters(
                                defectMeasurement.getMeasuredParameters(), calculation)))
                .toList();
        if (!repairsDb.isEmpty()) {
            updateAll(repairsDb, repairs);
            repository.saveAll(repairsDb);
            return;
        }
        repository.saveAll(repairs);
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