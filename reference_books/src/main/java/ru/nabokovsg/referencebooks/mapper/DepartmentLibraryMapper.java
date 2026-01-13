package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.NewDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseShortDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.UpdateDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.model.BranchLibrary;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;

@Mapper(componentModel = "spring")
public interface DepartmentLibraryMapper {

    @Mapping(source = "departmentDto.fullName", target = "fullName")
    @Mapping(source = "departmentDto.shortName", target = "shortName")
    @Mapping(source = "branch", target = "branch")
    @Mapping(target = "heatSupplySources", ignore = true)
    @Mapping(target = "id", ignore = true)
    DepartmentLibrary mapToDepartment(NewDepartmentLibraryDto departmentDto, BranchLibrary branch);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "heatSupplySources", ignore = true)
    void mapToUpdateDepartment(@MappingTarget DepartmentLibrary department, UpdateDepartmentLibraryDto departmentDto);

    ResponseDepartmentLibraryDto mapToDepartmentDto(DepartmentLibrary department);

    ResponseShortDepartmentLibraryDto mapToResponseShortDepartmentLibraryDto(DepartmentLibrary department);
}