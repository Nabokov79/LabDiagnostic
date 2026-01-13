package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;

import java.util.List;

public interface ResidualThicknessLibraryService {

    ResponseResidualThicknessLibraryDto save(NewResidualThicknessLibraryDto thicknessDto);

    ResponseResidualThicknessLibraryDto update(UpdateResidualThicknessLibraryDto thicknessDto);

    ResponseResidualThicknessLibraryDto get(Long id);

    List<ResponseResidualThicknessLibraryDto> getAll(Long equipmentLibraryId);

    void delete(Long id);
}