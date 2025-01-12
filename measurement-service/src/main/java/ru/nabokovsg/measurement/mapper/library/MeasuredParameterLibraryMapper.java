package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

@Mapper(componentModel = "spring")
public interface MeasuredParameterLibraryMapper {

    MeasurementParameterLibrary mapToMeasuredParameter(NewMeasurementParameterLibraryDto parameter);
    MeasurementParameterLibrary mapToUpdateMeasuredParameter(UpdateMeasurementParameterLibraryDto parameter);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elementRepair", ignore = true)
    void mapWithDefect(@MappingTarget MeasurementParameterLibrary parameter, DefectLibrary defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "defect", ignore = true)
    void mapWithRepair(@MappingTarget MeasurementParameterLibrary parameter, RepairLibrary elementRepair);
}