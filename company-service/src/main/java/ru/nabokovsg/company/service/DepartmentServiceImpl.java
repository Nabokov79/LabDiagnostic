package ru.nabokovsg.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.department.NewDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseShortDepartmentDto;
import ru.nabokovsg.company.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mapper.DepartmentMapper;
import ru.nabokovsg.company.model.Department;
import ru.nabokovsg.company.repository.DepartmentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final AddressService addressService;
    private final BranchService branchService;

    @Override
    public ResponseShortDepartmentDto save(NewDepartmentDto departmentDto) {
        if (repository.existsByFullName(departmentDto.getFullName())) {
            throw new BadRequestException(
                    String.format("Department with full name=%s is found", departmentDto.getFullName())
            );
        }
        return mapper.mapToShortDepartmentDto(
                repository.save(repository.save(map(mapper.mapToDepartment(departmentDto)
                                                  , departmentDto.getBranchId()
                                                  , departmentDto.getAddressId()))));
    }

    @Override
    public ResponseShortDepartmentDto update(UpdateDepartmentDto departmentDto) {
        if (repository.existsById(departmentDto.getId())) {
            return mapper.mapToShortDepartmentDto(
                    repository.save(repository.save(map(mapper.mapToUpdateDepartment(departmentDto)
                                                                , departmentDto.getBranchId()
                                                                , departmentDto.getAddressId()))));
        }
        throw new NotFoundException(
                String.format("Department with name=%s not found for update.", departmentDto.getFullName()));
    }

    @Override
    public ResponseDepartmentDto get(Long id) {
        return mapper.mapToFullDepartmentDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Department with id=%s not found", id))));
    }

    @Override
    public List<ResponseShortDepartmentDto> getAll(Long branchId, String name) {
        Set<Department> departments;
        if (branchId != null && branchId > 0) {
            departments = repository.findByBranchId(branchId);
        } else {
            departments = new HashSet<>(repository.findAll());
        }
        if (name != null) {
            String fullName = name.toLowerCase();
            departments = departments.stream()
                                     .filter(v -> v.getFullName().toLowerCase().contains(fullName))
                                     .collect(Collectors.toSet());
        }
        return departments.stream()
                          .map(mapper::mapToShortDepartmentDto)
                          .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Department with id=%s not found for delete.", id));
    }

    private Department map(Department department, Long branchId, Long addressId) {
        return mapper.mapWithData(department
                                , addressService.getAddressString(addressId)
                                , branchService.getById(branchId));
    }
}