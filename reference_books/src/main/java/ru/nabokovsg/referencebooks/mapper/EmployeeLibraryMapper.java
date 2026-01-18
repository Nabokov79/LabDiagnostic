package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.NewEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseShortEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.UpdateEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;
import ru.nabokovsg.referencebooks.model.EmployeeLibrary;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;

@Mapper(componentModel = "spring")
public interface EmployeeLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "initials", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    EmployeeLibrary mapToEmployeeLibrary(NewEmployeeLibraryDto employeeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "initials", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    void mapToUpdateEmployeeLibrary(@MappingTarget EmployeeLibrary employee, UpdateEmployeeLibraryDto employeeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "initials", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "patronymic", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "email", ignore = true)
    void mapToBranchEmployee(@MappingTarget EmployeeLibrary employee, String branch);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "initials", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "patronymic", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(source = "department.branch.fullName", target = "branch")
    @Mapping(source = "department.fullName", target = "department")
    void mapToDepartmentEmployee(@MappingTarget EmployeeLibrary employee, DepartmentLibrary department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "initials", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "patronymic", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(source = "source.department.branch.fullName", target = "branch")
    @Mapping(source = "source.department.fullName", target = "department")
    @Mapping(source = "source.source", target = "source")
    void mapToSourceEmployee(@MappingTarget EmployeeLibrary employee, HeatSupplySourceLibrary source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "patronymic", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "email", ignore = true)
    void mapToInitialsEmployee(@MappingTarget EmployeeLibrary employee, String initials, String fullName);

    ResponseEmployeeLibraryDto mapToResponseEmployeeLibraryDto(EmployeeLibrary employee);

    ResponseShortEmployeeLibraryDto mapToResponseShortEmployeeLibraryDto(EmployeeLibrary employee);
}