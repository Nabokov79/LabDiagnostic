package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;

import java.util.List;

public interface EmployeeLibraryService {

    ResponseEmployeeLibraryDto save(NewBranchLibraryDto branchDto);

    ResponseEmployeeLibraryDto update(UpdateBranchLibraryDto branchDto);

    ResponseEmployeeLibraryDto get(Long id);

    List<ResponseEmployeeLibraryDto> getAll(Long id, String department, String name);

    void delete(Long id);
}