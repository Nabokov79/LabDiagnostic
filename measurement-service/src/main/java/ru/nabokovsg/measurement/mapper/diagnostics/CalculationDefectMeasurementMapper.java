package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationDefectMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "parametersString", target = "parametersString")
    CalculationDefectMeasurement mapToCalculationDefectMeasurement(DefectMeasurement defect
                                                                 , Long defectId
                                                                 , String parametersString);

    void mapToUpdateMeasuredParameters(@MappingTarget CalculationDefectMeasurement defect
                                                    , Boolean unacceptable
                                                    , String parametersString);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "defect.id", target = "defectId")
    void mapToUpdateCalculationDefectMeasurement(@MappingTarget CalculationDefectMeasurement defectDb
                                                              , DefectMeasurement defect);
}