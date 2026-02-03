package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.mapper.MeasurementParameterLibraryMapper;
import ru.nabokovsg.referencebooks.repository.MeasurementParameterLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.CreateMeasurementParameterLibraryService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuredParameterLibraryServiceImpl implements MeasuredParameterLibraryService {

    private final MeasurementParameterLibraryRepository repository;
    private final MeasurementParameterLibraryMapper mapper;
    private final CreateMeasurementParameterLibraryService create;

    @Override
    public void saveDefectParameter(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParameters) {
        if (measuredParameters != null) {
            measuredParameters.forEach(parameter -> mapper.mapWithDefectLibrary(parameter, defect));
            repository.saveAll(measuredParameters);
        }
    }

    @Override
    public void saveRepairParameter(RepairLibrary repair, List<MeasurementParameterLibrary> measuredParameters) {
         if (measuredParameters != null) {
            measuredParameters.forEach(parameter -> mapper.mapWithRepairLibrary(parameter, repair));
            repository.saveAll(measuredParameters);
        }
    }

    @Override
    public List<MeasurementParameterLibrary> create(List<MeasurementParameterLibraryDto> measuredParameters) {
        if (measuredParameters == null) {
            return null;
        }
        return measuredParameters.stream().map(create::create).toList();
    }

    @Override
    public void update(List<MeasurementParameterLibrary> measuredParametersLibrary
                     , List<MeasurementParameterLibraryDto> measuredParameters) {
        if (measuredParameters != null) {
            if (measuredParametersLibrary.size() == measuredParameters.size()) {
                create.replaceEquals(measuredParametersLibrary, measuredParameters);
            }
            if (measuredParametersLibrary.size() > measuredParameters.size()) {
                List<Long> delete = new ArrayList<>(measuredParametersLibrary.size() - measuredParameters.size());
                create.replaceMore(measuredParametersLibrary, measuredParameters, delete);
                if (!delete.isEmpty()) {
                    repository.deleteAllById(delete);
                }
            }
            if (measuredParametersLibrary.size() < measuredParameters.size()) {
                create.replaceLess(measuredParametersLibrary, measuredParameters);
            }
        } else {
            repository.deleteAllById(measuredParametersLibrary.stream().map(MeasurementParameterLibrary::getId).toList());
            measuredParametersLibrary.clear();
        }
    }
}