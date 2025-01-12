package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

@Mapper(componentModel = "spring")
public interface DefectLibraryMapper {

    @Mapping(target = "measuredParameters", ignore = true)
    DefectLibrary mapToTypeDefectLibrary(NewDefectLibraryDto defectDto);

    @Mapping(target = "measuredParameters", ignore = true)
    void mapToUpdateTypeDefectLibrary(@MappingTarget DefectLibrary defect, UpdateDefectLibraryDto defectDto);

    void mapWithParameterCalculationType(@MappingTarget DefectLibrary defect, ParameterCalculationType calculation);

    ResponseDefectLibraryDto mapToResponseTypeDefectLibraryDto(DefectLibrary defect);

    ResponseShortDefectLibraryDto mapToResponseShortTypeDefectLibraryDto(DefectLibrary defect);
}