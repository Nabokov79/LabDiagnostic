package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.DefectMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.repository.diagnostics.DefectMeasurementRepository;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;
import ru.nabokovsg.measurement.сalculation.CalculationDefectMeasurementService;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementDuplicateServiceImpl implements MeasurementDuplicateService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final CalculationDefectMeasurementService calculationService;
    private final MeasuredParameterService measuredParameterService;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public DefectMeasurement get(DefectMeasurement defect, Set<DefectMeasurement> defects, Map<Long, Double> parameters) {
        parameters = replaceParameters(defect, parameters);
        if (!defects.isEmpty()) {
            for (DefectMeasurement duplicate : defects) {
                if (compare(duplicate.getMeasuredParameters(), parameters)) {
                    measuredParameterService.updateDuplicate(defect.getMeasuredParameters());
                    mapper.mapToParametersString(defect, stringBuilder.convertMeasuredParameter(defect.getMeasuredParameters()));
                    calculationService.calculationCalculationDefectManager(duplicate, defects);
                    return repository.save(duplicate);
                }
            }
        }
        return defect;
    }

    private Map<Long, Double> replaceParameters(DefectMeasurement defect, Map<Long, Double> parameters) {
        if (defect.getId() != null) {
            return defect.getMeasuredParameters()
                    .stream()
                    .collect(Collectors.toMap(MeasuredParameter::getParameterId
                            , parameter -> parameters.get(parameter.getId())));
        } else {
            return parameters;
        }
    }

    private boolean compare(Set<MeasuredParameter> measuredParameters, Map<Long, Double> parameters) {
        int coincidences = 0;
        String quantityName = MeasurementParameterType.QUANTITY.label;
        for (MeasuredParameter parameterDb : measuredParameters) {
            Double value = parameters.get(parameterDb.getParameterId());
            if (parameterDb.getParameterName().equals(quantityName)) {
                coincidences++;
            } else if (parameterDb.getValue().equals(value)) {
                coincidences++;
            }
        }
        if (coincidences == parameters.size()) {
            sumQuantityMeasurement(measuredParameters, parameters);
        }
        return coincidences == parameters.size();
    }

    private void sumQuantityMeasurement(Set<MeasuredParameter> measuredParameters, Map<Long, Double> parameters) {
        measuredParameters
                .forEach(parameter -> {
                    if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                        parameter.setValue(parameter.getValue() + parameters.get(parameter.getParameterId()));
                    }
                });
    }
}