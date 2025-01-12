package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.dto.branch.NewBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseShortBranchDto;
import ru.nabokovsg.company.dto.branch.UpdateBranchDto;
import ru.nabokovsg.company.model.Branch;
import ru.nabokovsg.company.model.Organization;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    Branch mapToBranch(NewBranchDto branchDto);

    Branch mapToUpdateBranch(UpdateBranchDto branchDto);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "organization", target = "organization")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "shortName", ignore = true)
    Branch mapWithData(@MappingTarget Branch branch, String address, Organization organization);

    ResponseBranchDto mapToBranchDto(Branch branch);

    ResponseShortBranchDto mapToShortBranchDto(Branch branch);
}