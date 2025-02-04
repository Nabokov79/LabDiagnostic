package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationDefectMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.*;
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
    public void calculationCalculationDefectManager(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        switch (defect.getCalculation()) {
            case MIN, MAX, MAX_MIN -> saveCalculationMinMax(defect, defects);
            case NO_ACTION -> saveWithoutCalculation(defect);
        }
    }

    @Override
    public void deleteCalculationDefectManager(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        switch (defect.getCalculation()) {
            case MIN, MAX, MAX_MIN -> {
                if (defects.isEmpty()) {
                    delete(defect);
                } else {
                    calculationCalculationDefectManager(defect, defects);
                }
            }
            case NO_ACTION -> deleteByDefectId(defect);
        }
    }

    private void saveCalculationMinMax(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        Set<MeasuredParameter> parameters = defects.stream()
                .map(DefectMeasurement::getMeasuredParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        CalculationDefectMeasurement calculationDefect = get(defect);
        String parametersString = calculationParameterService.calculateMeasuredParameters(parameters
                                                                                        , defect.getCalculation());
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, parametersString);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationDefect, defect.getUnacceptable(), parametersString);
        }
        repository.save(calculationDefect);
    }

    private void saveWithoutCalculation(DefectMeasurement defect) {
        CalculationDefectMeasurement calculationDefect = repository.findByDefectId(defect.getId());
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, defect.getParametersString());
        } else {
            mapper.mapToUpdateCalculationDefectMeasurement(calculationDefect, defect);
        }
        repository.save(calculationDefect);
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

    @Override
    public void delete(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            repository.deleteByEquipmentIdAndElementIdAndPartElementIdAndDefectName(defect.getEquipmentId()
                                                                                  , defect.getElementId()
                                                                                  , defect.getPartElementId()
                                                                                  , defect.getDefectName());
        } else {
            repository.deleteByEquipmentIdAndElementIdAndDefectName(defect.getEquipmentId()
                                                                  , defect.getElementId()
                                                                  , defect.getDefectName());
        }
    }

    @Override
    public void deleteByDefectId(DefectMeasurement defect) {
        repository.deleteByDefectId(defect.getId());
    }
}