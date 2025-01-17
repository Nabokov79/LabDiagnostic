package ru.nabokovsg.measurement.service.synchronizing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.library.SynchronizingMapper;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.library.RepairLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.DefectMeasurementRepository;
import ru.nabokovsg.measurement.repository.diagnostics.MeasuredParameterRepository;
import ru.nabokovsg.measurement.repository.diagnostics.RepairMeasurementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SynchronizingServiceImpl implements SynchronizingService {

    private final DefectMeasurementRepository defectRepository;
    private final RepairMeasurementRepository repairRepository;
    private final MeasuredParameterRepository parameterRepository;
    private final SynchronizingMapper mapper;

    @Override
    public void updateDefectName(DefectLibrary defectLibrary) {
        Set<DefectMeasurement> defects = defectRepository.findAllByDefectLibraryId(defectLibrary.getId());
        Map<Long, String> parametersName = defectLibrary.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(MeasurementParameterLibrary::getId
                        , MeasurementParameterLibrary::getParameterName));
        defects.forEach(defect -> {
            mapper.updateDefectName(defect, defectLibrary.getDefectName());
            updateDefectMeasuredParameterName(defect.getMeasuredParameters(), parametersName, defect);
        });
        defectRepository.saveAll(defects);
    }

    @Override
    public void updateRepairName(RepairLibrary repairLibrary) {
        Set<RepairMeasurement> repairs = repairRepository.findAllByRepairLibraryId(repairLibrary.getId());
        Map<Long, String> parametersName = repairLibrary.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(MeasurementParameterLibrary::getId
                        , MeasurementParameterLibrary::getParameterName));
        repairs.forEach(repair -> {
            mapper.updateRepairName(repair, repairLibrary.getRepairName());
            updateRepairMeasuredParameterName(repair.getMeasuredParameters(), parametersName, repair);
        });
        repairRepository.saveAll(repairs);
    }

    private void updateDefectMeasuredParameterName(Set<MeasuredParameter> measuredParameters
                                                 , Map<Long, String> parametersName
                                                 , DefectMeasurement defect) {
        measuredParameters.forEach(parameter -> mapper.updateDefectMeasuredParameterName(parameter
                                                                        , parametersName.get(parameter.getParameterId())
                                                                        , defect));
        parameterRepository.saveAll(measuredParameters);
    }

    private void updateRepairMeasuredParameterName(Set<MeasuredParameter> measuredParameters
                                                , Map<Long, String> parametersName
                                                , RepairMeasurement repair) {
        measuredParameters.forEach(parameter -> mapper.updateRepairMeasuredParameterName(parameter
                                                                        , parametersName.get(parameter.getParameterId())
                                                                        , repair));
        parameterRepository.saveAll(measuredParameters);
    }
}