package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationDefectMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationDefectMeasurementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationDefectMeasurementServiceImpl implements CalculationDefectMeasurementService {

    private final CalculationDefectMeasurementRepository repository;
    private final CalculationDefectMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;

    @Override
    public void factory(DefectMeasurement defect
                      , Set<DefectMeasurement> defectMeasurements
                      , ParameterCalculationType calculation) {
        switch (calculation) {
            case MIN, MAX, MAX_MIN -> {
                Set<MeasuredParameter> measuredParameters = defectMeasurements.stream()
                                                                        .map(DefectMeasurement::getMeasuredParameters)
                                                                        .flatMap(Collection::stream)
                                                                        .collect(Collectors.toSet());
                saveOne(defect, measuredParameters, calculation);
            }
            case NO_ACTION -> saveAll(defect, defectMeasurements, calculation);
        }
    }

    private void saveOne(DefectMeasurement defect
                       , Set<MeasuredParameter> measuredParameters
                       , ParameterCalculationType calculation) {
        CalculationDefectMeasurement calculationDefect = get(defect);
        String parameters = calculationParameterService.getMeasuredParameters(measuredParameters, calculation);
        if (calculationDefect == null) {
            repository.save(mapper.mapToCalculationDefectMeasurement(defect, parameters));
            return;
        }
        repository.save(mapper.mapToUpdateMeasuredParameters(calculationDefect, parameters));
    }

    private void saveAll(DefectMeasurement defect
                       , Set<DefectMeasurement> defectMeasurements
                       , ParameterCalculationType calculation) {
        Set<CalculationDefectMeasurement> defectsDb = getAll(defect);
        List<CalculationDefectMeasurement> defects = defectMeasurements
                .stream()
                .map(defectMeasurement ->  mapper.mapToCalculationDefectMeasurement(defect
                                                       , calculationParameterService.getMeasuredParameters(
                                                               defectMeasurement.getMeasuredParameters(), calculation)))
                .toList();
        if (!defectsDb.isEmpty()) {
            updateAll(defectsDb, defects);
            repository.saveAll(defectsDb);
            return;
        }
        repository.saveAll(defects);
    }

    private void updateAll(Set<CalculationDefectMeasurement> defectsDb, List<CalculationDefectMeasurement> defects) {
        int index = 0;
        for (CalculationDefectMeasurement defect : defectsDb) {
            mapper.mapToUpdateCalculationDefectMeasurement(defects.get(index), defect);
            index++;
        }
        repository.saveAll(defectsDb);
    }

    private Set<CalculationDefectMeasurement> getAll(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectName(defect.getEquipmentId()
                    , defect.getElementId()
                    , defect.getPartElementId()
                    , defect.getDefectName());
        }
        return repository.findAllByEquipmentIdAndElementIdAndDefectName(defect.getEquipmentId()
                , defect.getElementId()
                , defect.getDefectName());
    }

    private CalculationDefectMeasurement get(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            return repository.findByEquipmentIdAndElementIdAndPartElementIdAndDefectName(defect.getEquipmentId()
                    , defect.getElementId()
                    , defect.getPartElementId()
                    , defect.getDefectName());
        }
        return repository.findByEquipmentIdAndElementIdAndDefectName(defect.getEquipmentId()
                , defect.getElementId()
                , defect.getDefectName());
    }
}