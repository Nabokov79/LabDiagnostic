package ru.nabokovsg.measurement.service.diagnostics;

import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DuplicateSearchServiceImpl implements DuplicateSearchService {

    @Override
    public DefectMeasurement searchDefectDuplicate(DefectMeasurement identifiedDefect
                                                            , Set<DefectMeasurement> identifiedDefects) {
        if (!identifiedDefects.isEmpty()) {
            Map<Long, MeasuredParameter> parameters = identifiedDefect.getMeasuredParameters()
                                                .stream()
                                                .collect(Collectors.toMap(MeasuredParameter::getParameterId, p -> p));
            for (DefectMeasurement defect : identifiedDefects) {
                if (compareMeasuredParameter(defect.getMeasuredParameters(), parameters)) {
                    return defect;
                }
            }
        }
        return identifiedDefect;
    }

    @Override
    public RepairMeasurement searchRepairDuplicate(RepairMeasurement completedRepair
                                                 , Set<RepairMeasurement> completedRepairs) {
        if (!completedRepairs.isEmpty()) {
            Map<Long, MeasuredParameter> parameters = completedRepair.getMeasuredParameters()
                                                 .stream()
                                                 .collect(Collectors.toMap(MeasuredParameter::getParameterId, p -> p));
            for (RepairMeasurement repair : completedRepairs) {
                if (compareMeasuredParameter(repair.getMeasuredParameters(), parameters)) {
                    return repair;
                }
            }
        }
        return completedRepair;
    }

    public boolean compareMeasuredParameter(Set<MeasuredParameter> measuredParameters
                                                         , Map<Long, MeasuredParameter> parameters) {
        int coincidences = 0;
        String quantityName = MeasurementParameterType.valueOf("QUANTITY").label;
        Map<MeasuredParameter, MeasuredParameter> quantity = new HashMap<>(1);
        for (MeasuredParameter parameterDb : measuredParameters) {
            MeasuredParameter parameter = parameters.get(parameterDb.getParameterId());
            if (parameterDb.getParameterName().equals(quantityName)) {
                quantity.put(parameterDb, parameter);
                coincidences++;
            } else if (parameterDb.getValue().equals(parameter.getValue())) {
                coincidences++;
            }
        }
        if (coincidences == parameters.size()) {
            quantity.forEach((k, v) -> {
                measuredParameters.remove(k);
                k.setValue(k.getValue() + v.getValue());
                measuredParameters.add(k);
            });
            return true;
        }
        return false;
    }
}