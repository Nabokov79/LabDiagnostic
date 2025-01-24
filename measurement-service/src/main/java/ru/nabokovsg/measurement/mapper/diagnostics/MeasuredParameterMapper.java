package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;

@Mapper(componentModel = "spring")
public interface MeasuredParameterMapper {

    @Mapping(source = "parameterLibrary.id", target = "parameterId")
    @Mapping(source = "parameterLibrary.parameterName", target = "parameterName")
    @Mapping(source = "parameterLibrary.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "value", target = "value")
    @Mapping(target = "defect", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "repair", ignore = true)
    MeasuredParameter mapToMeasuredParameter(MeasurementParameterLibrary parameterLibrary, Double value);


    @Mapping(source = "defect", target = "defect")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "repair", ignore = true)
    void mapWithDefect(@MappingTarget MeasuredParameter parameter, DefectMeasurement defect);

    @Mapping(target = "id", ignore = true)
    void mapWithWeldDefect(@MappingTarget MeasuredParameter parameter, WeldDefectControl weldDefect);

    @Mapping(source = "repair", target = "repair")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "defect", ignore = true)
    @Mapping(target = "weldDefect", ignore = true)
    void mapWithRepair(@MappingTarget MeasuredParameter parameter, RepairMeasurement repair);

    @Mapping(source = "value", target = "value")
    void mapToUpdateMeasuredParameter(@MappingTarget MeasuredParameter parameter, Double value);
}