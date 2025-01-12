package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.department.NewDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseShortDepartmentDto;
import ru.nabokovsg.company.dto.department.UpdateDepartmentDto;

import java.util.List;

public interface DepartmentService {

    ResponseShortDepartmentDto save(NewDepartmentDto departmentDto);

    ResponseShortDepartmentDto update(UpdateDepartmentDto departmentDto);

    ResponseDepartmentDto get(Long id);

    List<ResponseShortDepartmentDto> getAll(Long branchId, String name);

    void delete(Long id);
}