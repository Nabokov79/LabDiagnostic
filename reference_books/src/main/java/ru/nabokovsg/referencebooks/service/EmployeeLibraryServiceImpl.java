package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.EmployeeLibraryMapper;
import ru.nabokovsg.referencebooks.model.EmployeeLibrary;
import ru.nabokovsg.referencebooks.repository.EmployeeLibraryRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeLibraryServiceImpl implements EmployeeLibraryService {

    private final EmployeeLibraryRepository repository;
    private final EmployeeLibraryMapper mapper;
    private final static String NOT_FOUND = "Сотрудник не найден.";

    @Override
    public ResponseEmployeeLibraryDto save(NewBranchLibraryDto branchDto) {
        return null;
    }

    @Override
    public ResponseEmployeeLibraryDto update(UpdateBranchLibraryDto branchDto) {
        return null;
    }

    @Override
    public ResponseEmployeeLibraryDto get(Long id) {
        return mapper.mapToResponseEmployeeLibraryDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND)));
    }

    @Override
    public List<ResponseEmployeeLibraryDto> getAll(Long id, String department, String name) {
        Set<EmployeeLibrary> employees = new HashSet<>();
        switch (department) {
            case "branch" -> employees = repository.findAllByBranchId(id);
            case "department" -> employees = repository.findAllByDepartmentId(id);
            case "source" -> employees = repository.findAllBySourceId(id);
        }
        if (employees.isEmpty()) {
            return new ArrayList<>();
        }
        if (name != null) {
            String employee = name.toLowerCase();
            return employees.stream()
                            .filter(employeeLibrary -> employeeLibrary.getSurname().toLowerCase().contains(employee)
                                                 || employeeLibrary.getPatronymic().toLowerCase().contains(employee)
                                                 || employeeLibrary.getName().toLowerCase().contains(employee))
                            .map(mapper::mapToResponseEmployeeLibraryDto)
                            .toList();
        }
        return employees.stream()
                        .map(mapper::mapToResponseEmployeeLibraryDto)
                        .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NOT_FOUND);
    }

    private void addDepartmentFields() {

    }

    private String createInitials(String name, String patronymic, String surname) {
        return String.join(" ", surname, patronymic, name);
    }
}