package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseShortBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.model.BranchLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.BranchLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.BranchLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchLibraryServiceImpl implements BranchLibraryService {

    private final BranchLibraryRepository repository;
    private final BranchLibraryMapper mapper;
    private final OrganizationLibraryService service;
    private final static String NOT_FOUND = "Филиал не обнаружен";

    @Override
    public ResponseShortBranchLibraryDto save(NewBranchLibraryDto branchDto) {
       exists(null, branchDto.getFullName());
        return mapper.mapToShortBranchDto(
                repository.save(mapper.mapToBranch(branchDto, service.getById(branchDto.getOrganizationId()))));
    }

    @Override
    public ResponseShortBranchLibraryDto update(UpdateBranchLibraryDto branchDto) {
        exists(branchDto.getId(), branchDto.getFullName());
        BranchLibrary branch = getById(branchDto.getId());
        mapper.mapToUpdateBranch(branch, branchDto);
        return mapper.mapToShortBranchDto(repository.save(branch));
    }

    @Override
    public ResponseBranchLibraryDto get(Long id) {
        return mapper.mapToBranchDto(getById(id));
    }

    @Override
    public List<ResponseShortBranchLibraryDto> getAll(Long id, String name) {
        Set<BranchLibrary> branches = repository.findAllByOrganizationId(id);
        if (name != null) {
            String branchName = name.toLowerCase();
            branches = branches.stream()
                    .filter(v -> v.getFullName().toLowerCase().contains(branchName)
                              || v.getShortName().toLowerCase().contains(branchName))
                    .collect(Collectors.toSet());
        }
        return branches.stream()
                .map(mapper::mapToShortBranchDto)
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
    public BranchLibrary getById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException(NOT_FOUND));

    }

    @Override
    public String getFullNameById(long id) {
        return repository.findFullNameById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
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