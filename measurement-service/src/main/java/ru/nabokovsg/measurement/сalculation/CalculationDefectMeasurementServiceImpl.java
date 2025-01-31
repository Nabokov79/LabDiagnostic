package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationDefectMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.*;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationDefectMeasurementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationDefectMeasurementServiceImpl implements CalculationDefectMeasurementService {

    private final CalculationDefectMeasurementRepository repository;
    private final CalculationDefectMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;

    @Override
    public void calculationManager(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        switch (defect.getCalculation()) {
            case MIN, MAX, MAX_MIN -> saveCalculationMinMax(defect, defects);
            case NO_ACTION -> saveWithoutCalculation(defect);
        }
    }

    @Override
    public void deleteManager(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        log.info("DELETE Manager:");
        log.info("defects ={}", defects);
        log.info("calculation ={}", defect.getCalculation());
        switch (defect.getCalculation()) {
            case MIN, MAX, MAX_MIN -> {
                if (defects.isEmpty()) {
                    deleteAll(defect);
                } else {
                    saveCalculationMinMax(defect, defects);
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
        String parametersString = calculationParameterService.calculateMeasuredParameters(parameters, defect.getCalculation());
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, defect.getId(), parametersString);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationDefect, defect.getUnacceptable(), parametersString);
        }
        repository.save(calculationDefect);
    }

    private void saveWithoutCalculation(DefectMeasurement defect) {
        CalculationDefectMeasurement calculationDefect = repository.findByDefectId(defect.getId());
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, defect.getId(), defect.getParametersString());
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

    private void deleteAll(DefectMeasurement defect) {
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

    private void deleteByDefectId(DefectMeasurement defect) {
        log.info("delete defect={}", defect);
        repository.deleteByDefectId(defect.getId());
    }
}