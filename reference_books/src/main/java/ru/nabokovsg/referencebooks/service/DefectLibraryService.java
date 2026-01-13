package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;

import java.util.List;

public interface DefectLibraryService {

    ResponseDefectLibraryDto save(NewDefectLibraryDto defectDto);

    ResponseDefectLibraryDto update(UpdateDefectLibraryDto defectDto);

    ResponseDefectLibraryDto get(Long id);

    List<ResponseShortDefectLibraryDto> getAll(String name, String documentation);

    void delete(Long id);

    DefectLibrary getById(Long id);
}