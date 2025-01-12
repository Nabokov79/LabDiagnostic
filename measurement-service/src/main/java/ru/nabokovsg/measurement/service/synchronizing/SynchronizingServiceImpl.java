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
        Set<MeasuredParameter> parameters = new HashSet<>();
        Set<DefectMeasurement> defects = defectRepository.findAllByDefectLibraryId(defectLibrary.getId());
        defects.forEach(defect -> {
                    parameters.addAll(defect.getMeasuredParameters());
                    mapper.updateDefectName(defect, defectLibrary.getDefectName());
                });
        defectRepository.saveAll(defects);
        updateMeasuredParameterName(parameters
                                 , defectLibrary.getMeasuredParameters()
                                                .stream()
                                                .collect(Collectors.toMap(MeasurementParameterLibrary::getId
                                                                     , MeasurementParameterLibrary::getParameterName)));
    }

    @Override
    public void updateRepairName(RepairLibrary repairLibrary) {
        Set<MeasuredParameter> parameters = new HashSet<>();
        Set<RepairMeasurement> repairs = repairRepository.findAllByRepairLibraryId(repairLibrary.getId());
        repairs.forEach(repair -> {
                    parameters.addAll(repair.getMeasuredParameters());
                    mapper. updateRepairName(repair, repairLibrary.getRepairName());
                });
        repairRepository.saveAll(repairs);
        updateMeasuredParameterName(parameters
                                  , repairLibrary.getMeasuredParameters()
                                                 .stream()
                                                 .collect(Collectors.toMap(MeasurementParameterLibrary::getId
                                                                     , MeasurementParameterLibrary::getParameterName)));
    }

    private void updateMeasuredParameterName(Set<MeasuredParameter> parameters, Map<Long, String> parametersLibrary) {
        parameters.forEach(parameter -> mapper.updateMeasuredParameterName(parameter
                                                                  , parametersLibrary.get(parameter.getParameterId())));
        parameterRepository.saveAll(parameters);
    }
}