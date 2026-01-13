package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;

@Mapper(componentModel = "spring")
public interface AcceptableResidualThicknessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    ResidualThicknessLibrary mapToAcceptableThickness(NewResidualThicknessLibraryDto thicknessDto
                                                              , String elementName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "elementLibraryId", ignore = true)
    @Mapping(target = "elementName", ignore = true)
    @Mapping(target = "partElementLibraryId", ignore = true)
    void mapToUpdateAcceptableThickness(@MappingTarget ResidualThicknessLibrary thickness
                                                     , UpdateResidualThicknessLibraryDto thicknessDto);

    ResponseResidualThicknessLibraryDto mapToResponseAcceptableResidualThicknessDto(
                                                                          ResidualThicknessLibrary thickness);
}