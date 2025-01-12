package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.measurement.model.library.DefectLibrary;

import java.util.List;

public interface DefectLibraryService {

    ResponseDefectLibraryDto save(NewDefectLibraryDto defectDto);

    ResponseDefectLibraryDto update(UpdateDefectLibraryDto defectDto);

    ResponseDefectLibraryDto get(Long id);

    List<ResponseShortDefectLibraryDto> getAll();

    void delete(Long id);

    DefectLibrary getById(Long id);
}