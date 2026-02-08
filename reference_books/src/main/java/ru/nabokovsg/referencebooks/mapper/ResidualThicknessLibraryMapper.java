package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseShortResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;

@Mapper(componentModel = "spring")
public interface ResidualThicknessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "equipmentFullName", ignore = true)
    @Mapping(target = "elementFullName", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    ResidualThicknessLibrary mapToAcceptableThickness(NewResidualThicknessLibraryDto thicknessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentFullName", ignore = true)
    @Mapping(target = "elementFullName", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    void mapToUpdateAcceptableThickness(@MappingTarget ResidualThicknessLibrary thickness
            , UpdateResidualThicknessLibraryDto thicknessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentationLibraryId", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "elementLibraryId", ignore = true)
    @Mapping(target = "partElementLibraryId", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minAcceptableThicknessMM", ignore = true)
    @Mapping(target = "minAcceptableThicknessPercent", ignore = true)
    @Mapping(target = "maxAcceptableThinningMM", ignore = true)
    @Mapping(target = "maxAcceptableThinningPercent", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    void mapToResidualThicknessLibrary(@MappingTarget ResidualThicknessLibrary thickness
                                                    , String equipmentFullName
                                                    , String elementFullName
                                                    , String documentationLibrary
                                                    , String standardSize);

    ResponseResidualThicknessLibraryDto mapToResponseAcceptableResidualThicknessDto(ResidualThicknessLibrary thickness);

    ResponseShortResidualThicknessLibraryDto mapToResponseShortResidualThicknessLibraryDto(ResidualThicknessLibrary thickness);
}