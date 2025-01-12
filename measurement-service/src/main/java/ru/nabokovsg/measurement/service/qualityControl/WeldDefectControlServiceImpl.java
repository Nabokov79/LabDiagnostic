package ru.nabokovsg.measurement.service.qualityControl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.NewWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.ResponseWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.UpdateWeldDefectControlDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.qualityControl.WeldDefectControlMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;
import ru.nabokovsg.measurement.repository.qualityControl.WeldDefectControlRepository;
import ru.nabokovsg.measurement.service.diagnostics.MeasuredParameterService;
import ru.nabokovsg.measurement.service.library.DefectLibraryService;
import ru.nabokovsg.measurement.сalculation.CalculationMeasuredParameterService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeldDefectControlServiceImpl implements WeldDefectControlService {

    private final WeldDefectControlRepository repository;
    private final WeldDefectControlMapper mapper;
    private final DefectLibraryService defectLibraryService;
    private final MeasuredParameterService measuredParameterService;
    private final CalculationMeasuredParameterService calculationParameterService;

    @Override
    public ResponseWeldDefectControlDto save(NewWeldDefectControlDto defectDto) {
        DefectLibrary defectLibrary = defectLibraryService.getById(defectDto.getDefectId());
        WeldDefectControl defect;
        if (defectDto.getDefectId() == null) {
            defect  = mapper.mapToWeldDefectControl(defectDto, defectLibrary.getDefectName(), "");
            saveWithPositiveQualityAssessment(defect);
            defect = repository.save(defect);
        } else {
            Set<MeasuredParameter> parameters = getMeasuredParameters(defectDto.getMeasuredParameters()
                                                                    , defectLibrary.getMeasuredParameters());
            defect = repository.save(mapper.mapToWeldDefectControl(defectDto
                            , defectLibrary.getDefectName()
                            , calculationParameterService.getMeasuredParameters(parameters
                                                                              , ParameterCalculationType.NO_ACTION)));
            setMeasuredParameters(defect, defectLibrary, parameters);
        }
        return mapper.mapToResponseWeldDefectControlDto(defect);
    }

    @Override
    public ResponseWeldDefectControlDto update(UpdateWeldDefectControlDto defectDto) {
        WeldDefectControl defect = getById(defectDto.getId());
        if (defectDto.getDefectId() == null) {
            saveWithPositiveQualityAssessment(defect);
            if (defect.getMeasuredParameters() != null) {
                measuredParameterService.deleteAll(defect.getMeasuredParameters());
            }
        } else {
            defect.setMeasuredParameters(
                    measuredParameterService.update(defect.getMeasuredParameters(), defectDto.getMeasuredParameters()));
            defect = mapper.mapToUpdateWeldDefectControl(defect
                        , calculationParameterService.getMeasuredParameters(defect.getMeasuredParameters()
                                                                          , ParameterCalculationType.NO_ACTION));
        }
        return mapper.mapToResponseWeldDefectControlDto(repository.save(defect));
    }

    @Override
    public ResponseWeldDefectControlDto get(Long id) {
        return mapper.mapToResponseWeldDefectControlDto(getById(id));
    }

    @Override
    public List<ResponseWeldDefectControlDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                         .stream()
                         .map(mapper::mapToResponseWeldDefectControlDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Weld defect with id=%s not found for delete", id));
    }

    private WeldDefectControl getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Weld defect with id=%s not found", id)));
    }

    private void saveWithPositiveQualityAssessment(WeldDefectControl defect) {
        mapper.mapToPositiveQualityAssessment(defect
                                                        , "Дефекты не обнаружены"
                                                        , "-"
                                                        , "удовл.");
    }

    private void setMeasuredParameters(WeldDefectControl defect
            , DefectLibrary defectLibrary
            , Set<MeasuredParameter> measuredParameters) {
        defect.setMeasuredParameters(measuredParameterService.save(
                new ParameterMeasurementBuilder.Builder()
                        .libraryDataType(MeasurementType.WELD_DEFECT)
                        .weldDefect(defect)
                        .measurementParameterLibraries(defectLibrary.getMeasuredParameters())
                        .measuredParameters(measuredParameters)
                        .build()));
    }

    private Set<MeasuredParameter> getMeasuredParameters(List<NewMeasuredParameterDto> measuredParameters
                                                       , Set<MeasurementParameterLibrary> measuredParametersLibrary) {
        Map<Long, MeasurementParameterLibrary> parametersLibrary = measuredParametersLibrary
                .stream()
                .collect(Collectors.toMap(MeasurementParameterLibrary::getId, parameter -> parameter));
        return measuredParameters.stream()
                .map(parameter ->
                        mapper.mapToMeasuredParameter(parametersLibrary.get(parameter.getParameterLibraryId())
                                , parameter.getValue()))
                .collect(Collectors.toSet());
    }
}