package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.NewDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseShortDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.UpdateDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DepartmentLibraryMapper;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.DepartmentLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentLibraryServiceImpl implements DepartmentLibraryService {

    private final DepartmentLibraryRepository repository;
    private final DepartmentLibraryMapper mapper;
    private final BranchLibraryService service;
    private final static String NOT_FOUND = "Подразделение не обнаружено.";

    @Override
    public ResponseShortDepartmentLibraryDto save(NewDepartmentLibraryDto departmentDto) {
        exists(null, departmentDto.getFullName());
        return mapper.mapToResponseShortDepartmentLibraryDto(
                repository.save(mapper.mapToDepartment(departmentDto, service.getById(departmentDto.getBranchId())))
        );
    }

    @Override
    public ResponseShortDepartmentLibraryDto update(UpdateDepartmentLibraryDto departmentDto) {
        exists(departmentDto.getId(), departmentDto.getFullName());
        DepartmentLibrary department = getById(departmentDto.getId());
        mapper.mapToUpdateDepartment(department, departmentDto);
        return mapper.mapToResponseShortDepartmentLibraryDto(repository.save(department));
    }

    @Override
    public ResponseDepartmentLibraryDto get(Long id) {
        return mapper.mapToDepartmentDto(getById(id));
    }

    @Override
    public List<ResponseShortDepartmentLibraryDto> getAll(Long id, String name) {
        Set<DepartmentLibrary> departments = repository.findAllByBranchIdOrderByFullName(id);
        if (name != null) {
            String departmentName = name.toLowerCase();
            departments = departments.stream()
                    .filter(v -> v.getFullName().toLowerCase().contains(departmentName)
                    || v.getShortName().toLowerCase().contains(departmentName))
                    .collect(Collectors.toSet());
        }
        return departments.stream()
                .map(mapper::mapToResponseShortDepartmentLibraryDto)
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

    @Override
    public DepartmentLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    private void exists(Long id, String fullName) {
        boolean exists;
        if (id != null) {
            exists = !id.equals(repository.findIdByFullName(fullName).orElse(id));
        } else {
            exists = repository.existsByFullName(fullName);
        }
        if (exists) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label,fullName));
        }
    }
}