package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.UpdateDefectMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.library.DefectLibrary;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    DefectMeasurement mapToDefectMeasurement(NewDefectMeasurementDto identifiedDefectDto);

    DefectMeasurement mapToUpdateDefectMeasurement(UpdateDefectMeasurementDto identifiedDefectDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    void mapWitDefectLibrary(@MappingTarget DefectMeasurement defect, DefectLibrary defectLibrary);

    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement identifiedDefect);
}