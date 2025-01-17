package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

@Mapper(componentModel = "spring")
public interface SynchronizingMapper {

    void updateDefectName(@MappingTarget DefectMeasurement defect, String defectName);

    void updateRepairName(@MappingTarget RepairMeasurement repair, String repairName);

    void updateDefectMeasuredParameterName(@MappingTarget MeasuredParameter parameter
                                                        , String parameterName
                                                        , DefectMeasurement defect);

    void updateRepairMeasuredParameterName(@MappingTarget MeasuredParameter parameter
                                                        , String parameterName
                                                        , RepairMeasurement repair);
}