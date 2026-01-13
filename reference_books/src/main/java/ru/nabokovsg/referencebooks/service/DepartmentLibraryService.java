package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.departmentLibrary.NewDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseShortDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.UpdateDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;

import java.util.List;

public interface DepartmentLibraryService {

    ResponseShortDepartmentLibraryDto save(NewDepartmentLibraryDto departmentDto);

    ResponseShortDepartmentLibraryDto update(UpdateDepartmentLibraryDto departmentDto);

    ResponseDepartmentLibraryDto get(Long id);

    List<ResponseShortDepartmentLibraryDto> getAll(Long id, String name);

   void delete(Long id);

    DepartmentLibrary getById(Long id);
}