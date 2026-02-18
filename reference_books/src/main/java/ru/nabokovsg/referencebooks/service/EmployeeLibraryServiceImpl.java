package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.NewEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseShortEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.UpdateEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.EmployeeLibraryMapper;
import ru.nabokovsg.referencebooks.model.EmployeeLibrary;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
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
    private final BranchLibraryService branchService;
    private final DepartmentLibraryService departmentService;
    private final HeatSupplySourceLibraryService sourceService;

    @Override
    public ResponseShortEmployeeLibraryDto save(NewEmployeeLibraryDto employeeDto) {
        exists(employeeDto.getEmail());
        EmployeeLibrary employee = mapper.mapToEmployeeLibrary(employeeDto);
        create(employee);
        return mapper.mapToResponseShortEmployeeLibraryDto(repository.save(employee));
    }

    @Override
    public ResponseShortEmployeeLibraryDto update(UpdateEmployeeLibraryDto employeeDto) {
        if (repository.existsById(employeeDto.getId())) {
            EmployeeLibrary employee = getById(employeeDto.getId());
            mapper.mapToUpdateEmployeeLibrary(employee, employeeDto);
            create(employee);
            return mapper.mapToResponseShortEmployeeLibraryDto(repository.save(employee));
        }
        throw new NotFoundException(String.format(NotFoundExceptionMassage.EMPLOYEE.label));
    }

    @Override
    public ResponseEmployeeLibraryDto get(Long id) {
        return mapper.mapToResponseEmployeeLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortEmployeeLibraryDto> getAll(Long id, String department, String name) {
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
                    .filter(employeeLibrary -> employeeLibrary.getFullName().toLowerCase().contains(employee))
                    .map(mapper::mapToResponseShortEmployeeLibraryDto)
                    .toList();
        }
        return employees.stream()
                .map(mapper::mapToResponseShortEmployeeLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.EMPLOYEE.label);
    }

    private void exists(String email) {
        if (repository.existsByEmail(email)) {
            throw new BadRequestException(
                    String.join("", BadRequestExceptionMassage.DUPLICATE.label, email));
        }
    }

    private EmployeeLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.EMPLOYEE.label));
    }

    private void create(EmployeeLibrary employee) {
        createInitials(employee);
        if (employee.getSourceId() != null) {
            mapper.mapToSourceEmployee(employee, sourceService.getById(employee.getSourceId()));
            return;
        }
        if (employee.getDepartmentId() != null) {
            mapper.mapToDepartmentEmployee(employee, departmentService.getById(employee.getDepartmentId()));
            return;
        }
        mapper.mapToBranchEmployee(employee, branchService.getFullNameById(employee.getBranchId()));
    }

    private void createInitials(EmployeeLibrary employee) {
        mapper.mapToInitialsEmployee(employee
                , String.join(" ", employee.getSurname()
                        , String.join("", String.valueOf(employee.getName().charAt(0)), ".")
                        , String.join("", String.valueOf(employee.getPatronymic().charAt(0)), "."))
                , String.join(" ", employee.getSurname(), employee.getName(), employee.getPatronymic()));
    }
}