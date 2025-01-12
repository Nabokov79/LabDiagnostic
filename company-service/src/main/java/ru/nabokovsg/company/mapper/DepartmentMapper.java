package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.dto.department.NewDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseShortDepartmentDto;
import ru.nabokovsg.company.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.company.model.Branch;
import ru.nabokovsg.company.model.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department mapToDepartment(NewDepartmentDto department);

    Department mapToUpdateDepartment(UpdateDepartmentDto department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "shortName", ignore = true)
    @Mapping(source = "address", target = "address")
    @Mapping(source = "branch", target = "branch")
    Department mapWithData(@MappingTarget Department department, String address, Branch branch);

    ResponseDepartmentDto mapToFullDepartmentDto(Department department);

    ResponseShortDepartmentDto mapToShortDepartmentDto(Department department);
}