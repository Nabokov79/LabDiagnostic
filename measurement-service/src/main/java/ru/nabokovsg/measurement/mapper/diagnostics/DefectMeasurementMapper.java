package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseShortDefectMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.DefectLibrary;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DefectMeasurementMapper {

    @Mapping(source = "measuredParameters", target = "measuredParameters")
    @Mapping(target = "id", ignore = true)
    DefectMeasurement mapToDefectMeasurement(NewDefectMeasurementDto defect
                                           , Set<MeasuredParameter> measuredParameters
                                           , DefectLibrary defectLibrary
                                           , EquipmentDto equipment
                                           , String parametersString);

    ResponseShortDefectMeasurementDto mapToResponseShortDefectMeasurementDto(DefectMeasurement defect);

    ResponseDefectMeasurementDto mapToResponseDefectMeasurementDto(DefectMeasurement defect);

    void mapToParametersString(@MappingTarget DefectMeasurement defect, String parametersString);
}