package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.mapper.MeasurementParameterLibraryMapper;
import ru.nabokovsg.referencebooks.repository.MeasurementParameterLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuredParameterLibraryServiceImpl implements MeasuredParameterLibraryService {

    private final MeasurementParameterLibraryRepository repository;
    private final MeasurementParameterLibraryMapper mapper;

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
    public List<MeasurementParameterLibrary> createNew(List<NewMeasurementParameterLibraryDto> measuredParameters) {
        if (measuredParameters != null) {
            return measuredParameters.stream()
                    .map(mapper::mapToMeasuredParameter)
                    .peek(this::replace)
                    .toList();
        }
        return null;
    }

    @Override
    public List<MeasurementParameterLibrary> createUpdate(List<UpdateMeasurementParameterLibraryDto> measuredParameters) {
       if (measuredParameters != null) {
           return measuredParameters.stream()
                   .map(mapper::mapToUpdateMeasuredParameter)
                   .peek(this::replace)
                   .toList();
       }
       return null;
    }

    private void replace(MeasurementParameterLibrary parameter){
        ParameterCalculationType calculationType = ParameterCalculationType.from(parameter.getCalculation())
                .orElseThrow(() -> new BadRequestException(String.format("Недопустимый тип расчета: %s", parameter.getCalculation())));
        mapper.mapToReplacement(parameter
                            , MeasurementParameterType.from(parameter.getName()).orElseThrow(
                                      () -> new BadRequestException(String.format("Недопустимое наименование параметра: %s", parameter.getName()))).label
                            , UnitMeasurementType.from(parameter.getUnitMeasurement()).orElseThrow(
                                     () -> new BadRequestException(String.format("Недопустимая единица измерения: %s", parameter.getUnitMeasurement()))).label
                            , calculationType
                            , calculationType.label);
    }
}