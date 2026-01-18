package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseShortBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.model.BranchLibrary;

import java.util.List;

public interface BranchLibraryService {

    ResponseShortBranchLibraryDto save(NewBranchLibraryDto branchDto);

    ResponseShortBranchLibraryDto update(UpdateBranchLibraryDto branchDto);

    ResponseBranchLibraryDto get(Long id);

    List<ResponseShortBranchLibraryDto> getAll(Long id, String name);

    void delete(Long id);

    BranchLibrary getById(Long id);

    String getFullNameById(long id);
}