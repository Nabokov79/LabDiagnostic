package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.employeeLibrary.NewEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseShortEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.UpdateEmployeeLibraryDto;

import java.util.List;

public interface EmployeeLibraryService {

    ResponseShortEmployeeLibraryDto save(NewEmployeeLibraryDto employeeDto);

    ResponseShortEmployeeLibraryDto update(UpdateEmployeeLibraryDto employeeDto);

    ResponseEmployeeLibraryDto get(Long id);

    List<ResponseShortEmployeeLibraryDto> getAll(Long id, String department, String name);

    void delete(Long id);
}