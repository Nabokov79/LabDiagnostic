package ru.nabokovsg.measurement.mapper.qualityControl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.weldDefectControl.NewWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.ResponseWeldDefectControlDto;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;

@Mapper(componentModel = "spring")
public interface WeldDefectControlMapper {

    WeldDefectControl mapToWeldDefectControl(NewWeldDefectControlDto defectDto, String defectName, String parametersString);

    WeldDefectControl mapToUpdateWeldDefectControl(@MappingTarget WeldDefectControl defect, String parametersString);
    ResponseWeldDefectControlDto mapToResponseWeldDefectControlDto(WeldDefectControl defect);

    void mapToPositiveQualityAssessment(@MappingTarget WeldDefectControl defect
                                                     , String parametersString
                                                     , String coordinates
                                                     , String qualityAssessment);

    @Mapping(source = "parameterLibrary.id", target = "parameterId")
    @Mapping(source = "parameterLibrary.parameterName", target = "parameterName")
    @Mapping(source = "parameterLibrary.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "value", target = "value")
    @Mapping(target = "id", ignore = true)
    MeasuredParameter mapToMeasuredParameter(MeasurementParameterLibrary parameterLibrary, Double value);
}