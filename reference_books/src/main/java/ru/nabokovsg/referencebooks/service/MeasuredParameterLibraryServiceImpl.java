package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.MeasurementParameterLibraryMapper;
import ru.nabokovsg.referencebooks.repository.MeasurementParameterLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuredParameterLibraryServiceImpl implements MeasuredParameterLibraryService {

    private final MeasurementParameterLibraryRepository repository;
    private final MeasurementParameterLibraryMapper mapper;
    private final static String MASSAGE = "Измеряемый параметр не обнаружен.";

    @Override
    public List<MeasurementParameterLibrary> saveNewDefectParameter(DefectLibrary defect
                                                        , List<NewMeasurementParameterLibraryDto> measuredParameters) {
        return repository.saveAll(measuredParameters.stream()
                                 .map(mapper::mapToMeasuredParameter)
                                 .peek(parameter -> {
                                     setNameDefectParameter(parameter
                                             , defect.getDefectsQuantity()
                                             , defect.getAssessmentAreaMM()
                                             , defect.getAssessmentAreaPercentage());
                                     mapper.mapUpdateUnitMeasurement(parameter
                                                        , getUnitMeasurementType(parameter.getUnitMeasurement()).label);
                                     setParameterCalculationType(parameter);
                                     mapper.mapWithDefectLibrary(parameter, defect);
                                 })
                                 .toList());
    }

    @Override
    public List<MeasurementParameterLibrary> saveNewRepairParameter(RepairLibrary repair
                                                        , List<NewMeasurementParameterLibraryDto> measuredParameters) {
        return repository.saveAll(measuredParameters.stream()
                                                    .map(mapper::mapToMeasuredParameter)
                                                    .peek(parameter -> {
                                                        setName(parameter);
                                                        mapper.mapUpdateUnitMeasurement(parameter
                                                       , getUnitMeasurementType(parameter.getUnitMeasurement()).label);
                                                        setParameterCalculationType(parameter);
                                                        mapper.mapWithRepairLibrary(parameter, repair);
                                                    })
                                                    .toList());
    }

    @Override
    public List<MeasurementParameterLibrary> update(List<UpdateMeasurementParameterLibraryDto> measuredParameters) {
        if (measuredParameters != null) {
            return repository.saveAll(measuredParameters.stream()
                    .map(mapper::mapToUpdateMeasuredParameter)
                    .peek(parameter -> {
                        mapper.mapUpdateName(parameter, getMeasurementParameterType(parameter.getName()).label);
                        mapper.mapUpdateUnitMeasurement(parameter
                                                      , getUnitMeasurementType(parameter.getUnitMeasurement()).label);
                        setParameterCalculationType(parameter);
                    })
                    .toList());
        }
        return null;
    }

    @Override
    public ResponseMeasurementParameterLibraryDto get(Long id) {
        return mapper.mapToResponseMeasurementParameterLibraryDto(getById(id));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }

    private MeasurementParameterLibrary getById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void setName(MeasurementParameterLibrary parameter) {
        mapper.mapUpdateName(parameter, getMeasurementParameterType(parameter.getName()).label);
    }

    private void setNameDefectParameter(MeasurementParameterLibrary parameter, Integer defectsQuantity, Double assessmentAreaMM, Double assessmentAreaPercentage) {
        MeasurementParameterType measurementParameterType = getMeasurementParameterType(parameter.getName());
        if (measurementParameterType.equals(MeasurementParameterType.QUANTITY) && defectsQuantity != null) {
            if (assessmentAreaMM != null) {
                mapper.mapUpdateName(parameter
                        , String.join(" ", measurementParameterType.label, "на", String.valueOf(assessmentAreaMM), "мм"));
                return;
            }
            if (assessmentAreaPercentage != null) {
                mapper.mapUpdateName(parameter
                        , String.join(" ", measurementParameterType.label, "на", String.valueOf(assessmentAreaPercentage), "% от Р св.соед."));
                return;
            }
        }
        mapper.mapUpdateName(parameter, measurementParameterType.label);
    }

    private MeasurementParameterType getMeasurementParameterType(String name) {
        return MeasurementParameterType.from(name)
                .orElseThrow(() -> new BadRequestException(String.format("Недопустимое наименование параметра: %s", name)));
    }

    private UnitMeasurementType getUnitMeasurementType(String unitMeasurement) {
        return UnitMeasurementType.from(unitMeasurement)
                .orElseThrow(() -> new BadRequestException(String.format("Недопустимая единица измерения: %s", unitMeasurement)));
    }

    private void setParameterCalculationType(MeasurementParameterLibrary parameter) {
        ParameterCalculationType calculationType = ParameterCalculationType.from(parameter.getCalculation())
                .orElseThrow(() -> new BadRequestException(String.format("Недопустимый тип расчета: %s", parameter.getCalculation())));
        mapper.mapUpdateParameterCalculationType(parameter, calculationType, calculationType.label);
    }
}