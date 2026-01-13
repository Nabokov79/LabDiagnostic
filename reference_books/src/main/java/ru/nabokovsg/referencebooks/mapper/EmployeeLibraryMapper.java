package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.NewEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.UpdateEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.model.EmployeeLibrary;

@Mapper(componentModel = "spring")
public interface EmployeeLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "initials", ignore = true)
    EmployeeLibrary mapToEmployeeLibrary(NewEmployeeLibraryDto employeeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "initials", ignore = true)
    void mapToUpdateEmployeeLibrary(@MappingTarget EmployeeLibrary employee, UpdateEmployeeLibraryDto employeeDto);

    void mapToDepartmentFields(@MappingTarget EmployeeLibrary employee, Long branchId, String branch, Long departmentId, String department, Long sourceId, String initials);

    ResponseEmployeeLibraryDto mapToResponseEmployeeLibraryDto(EmployeeLibrary employee);
}