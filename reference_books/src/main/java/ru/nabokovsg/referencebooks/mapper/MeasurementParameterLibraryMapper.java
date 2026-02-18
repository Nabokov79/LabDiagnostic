package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model_enum.ParameterCalculationType;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

@Mapper(componentModel = "spring")
public interface MeasurementParameterLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "defect", ignore = true)
    @Mapping(target = "repair", ignore = true)
    MeasurementParameterLibrary mapToMeasuredParameter(MeasurementParameterLibraryDto parameterLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "acceptableMinValueMM", ignore = true)
    @Mapping(target = "acceptableMinValuePercentage", ignore = true)
    @Mapping(target = "acceptableMaxValueMM", ignore = true)
    @Mapping(target = "acceptableMaxValuePercentage", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "repair", ignore = true)
    @Mapping(target = "defect", ignore = true)
    void mapToReplacement(@MappingTarget MeasurementParameterLibrary parameter
                                       , String name, String unitMeasurement
                                       , ParameterCalculationType calculationType, String calculation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "repair", ignore = true)
    @Mapping(target = "defect", ignore = true)
    @Mapping(source = "parameter.name", target = "name")
    @Mapping(source = "parameter.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "parameter.calculationType", target = "calculationType")
    @Mapping(source = "parameter.calculation", target = "calculation")
    @Mapping(source = "parameter.acceptableMinValueMM", target = "acceptableMinValueMM")
    @Mapping(source = "parameter.acceptableMinValuePercentage", target = "acceptableMinValuePercentage")
    @Mapping(source = "parameter.acceptableMaxValueMM", target = "acceptableMaxValueMM")
    @Mapping(source = "parameter.acceptableMaxValuePercentage", target = "acceptableMaxValuePercentage")
    @Mapping(source = "parameter.calculateByResidualThickness", target = "calculateByResidualThickness")
    void replace(@MappingTarget MeasurementParameterLibrary measuredParameter, MeasurementParameterLibrary parameter);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "unitMeasurement", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "calculation", ignore = true)
    @Mapping(target = "acceptableMinValueMM", ignore = true)
    @Mapping(target = "acceptableMinValuePercentage", ignore = true)
    @Mapping(target = "acceptableMaxValueMM", ignore = true)
    @Mapping(target = "acceptableMaxValuePercentage", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "repair", ignore = true)
    void mapWithDefectLibrary(@MappingTarget MeasurementParameterLibrary parameter, DefectLibrary defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "unitMeasurement", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "calculation", ignore = true)
    @Mapping(target = "acceptableMinValueMM", ignore = true)
    @Mapping(target = "acceptableMinValuePercentage", ignore = true)
    @Mapping(target = "acceptableMaxValueMM", ignore = true)
    @Mapping(target = "acceptableMaxValuePercentage", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "defect", ignore = true)
    void mapWithRepairLibrary(@MappingTarget MeasurementParameterLibrary parameter, RepairLibrary repair);
}