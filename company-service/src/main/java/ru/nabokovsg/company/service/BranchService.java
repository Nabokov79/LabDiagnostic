package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.branch.NewBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseShortBranchDto;
import ru.nabokovsg.company.dto.branch.UpdateBranchDto;
import ru.nabokovsg.company.model.Branch;

import java.util.List;

public interface BranchService {

    ResponseShortBranchDto save(NewBranchDto branchDto);

    ResponseShortBranchDto update(UpdateBranchDto branchDto);

    ResponseBranchDto get(Long id);

    Branch getById(Long id);

    List<ResponseShortBranchDto> getAll(Long id, String name);

    void  delete(Long id);
}