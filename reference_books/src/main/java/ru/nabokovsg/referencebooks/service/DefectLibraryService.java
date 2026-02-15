package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;

import java.util.List;

public interface DefectLibraryService {

    ResponseShortDefectLibraryDto save(NewDefectLibraryDto defectDto);

    ResponseShortDefectLibraryDto update(UpdateDefectLibraryDto defectDto);

    ResponseDefectLibraryDto get(Long id);

    List<ResponseShortDefectLibraryDto> getAll(String search);

    void delete(Long id);

    DefectLibrary getById(Long id);
}