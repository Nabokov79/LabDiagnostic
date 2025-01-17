package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.library.DefectLibrary;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    DefectMeasurement mapToDefectMeasurement(NewDefectMeasurementDto defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    void mapWitDefectLibrary(@MappingTarget DefectMeasurement defect
                                          , DefectLibrary defectLibrary
                                          , String elementName
                                          , String partElementName);

    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement identifiedDefect);
}