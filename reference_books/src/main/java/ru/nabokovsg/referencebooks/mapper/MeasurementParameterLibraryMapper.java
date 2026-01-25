package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.ParameterCalculationType;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

@Mapper(componentModel = "spring")
public interface MeasurementParameterLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "defect", ignore = true)
    @Mapping(target = "repair", ignore = true)
    MeasurementParameterLibrary mapToMeasuredParameter(NewMeasurementParameterLibraryDto parameterLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "defect", ignore = true)
    @Mapping(target = "repair", ignore = true)
    MeasurementParameterLibrary mapToUpdateMeasuredParameter(UpdateMeasurementParameterLibraryDto parameterLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "acceptableMinValue", ignore = true)
    @Mapping(target = "acceptableMaxValue", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "repair", ignore = true)
    @Mapping(target = "defect", ignore = true)
    void mapToReplacement(@MappingTarget MeasurementParameterLibrary parameter
                                       , String name, String unitMeasurement
                                       , ParameterCalculationType calculationType, String calculation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "unitMeasurement", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "calculation", ignore = true)
    @Mapping(target = "acceptableMinValue", ignore = true)
    @Mapping(target = "acceptableMaxValue", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "repair", ignore = true)
    void mapWithDefectLibrary(@MappingTarget MeasurementParameterLibrary parameter, DefectLibrary defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "unitMeasurement", ignore = true)
    @Mapping(target = "calculationType", ignore = true)
    @Mapping(target = "calculation", ignore = true)
    @Mapping(target = "acceptableMinValue", ignore = true)
    @Mapping(target = "acceptableMaxValue", ignore = true)
    @Mapping(target = "calculateByResidualThickness", ignore = true)
    @Mapping(target = "defect", ignore = true)
    void mapWithRepairLibrary(@MappingTarget MeasurementParameterLibrary parameter, RepairLibrary repair);
}