package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.MeasurementDuplicateMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MeasurementDuplicateServiceImpl implements MeasurementDuplicateService {

    private final MeasurementDuplicateMapper mapper;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public DefectMeasurement searchDefectMeasurementDuplicate(Set<DefectMeasurement> defects
                                                            , Map<Long, Double> parameters) {
        if (!defects.isEmpty()) {
            for (DefectMeasurement duplicate : defects) {
                if (compare(duplicate.getMeasuredParameters(), parameters)) {
                    sumQuantityMeasurement(duplicate.getMeasuredParameters(), parameters);
                    mapper.mapToUpdateDefectMeasurement(duplicate
                            , stringBuilder.convertMeasuredParameter(duplicate.getMeasuredParameters()));
                    return duplicate;
                }
            }
        }
        return null;
    }

    @Override
    public RepairMeasurement searchRepairMeasurementDuplicate(Set<RepairMeasurement> repairs, Map<Long, Double> parameters) {
        if (!repairs.isEmpty()) {
            for (RepairMeasurement duplicate : repairs) {
                if (compare(duplicate.getMeasuredParameters(), parameters)) {
                    sumQuantityMeasurement(duplicate.getMeasuredParameters(), parameters);
                    mapper.mapToUpdateRepairMeasurement(duplicate
                            , stringBuilder.convertMeasuredParameter(duplicate.getMeasuredParameters()));
                    return duplicate;
                }
            }
        }
        return null;
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