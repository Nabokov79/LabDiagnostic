package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseShortBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.model.BranchLibrary;
import ru.nabokovsg.referencebooks.model.OrganizationLibrary;

@Mapper(componentModel = "spring")
public interface BranchLibraryMapper {

    @Mapping(source = "branchDto.fullName", target = "fullName")
    @Mapping(source = "branchDto.shortName", target = "shortName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departments", ignore = true)
    BranchLibrary mapToBranch(NewBranchLibraryDto branchDto, OrganizationLibrary organization);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "departments", ignore = true)
    void mapToUpdateBranch(@MappingTarget BranchLibrary branch, UpdateBranchLibraryDto branchDto);

    ResponseShortBranchLibraryDto mapToShortBranchDto(BranchLibrary branch);

    ResponseBranchLibraryDto mapToBranchDto(BranchLibrary branch);
}