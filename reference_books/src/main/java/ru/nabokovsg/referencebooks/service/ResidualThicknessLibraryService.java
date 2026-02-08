package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseShortResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;

import java.util.List;

public interface ResidualThicknessLibraryService {

    ResponseShortResidualThicknessLibraryDto save(NewResidualThicknessLibraryDto thicknessDto);

    ResponseShortResidualThicknessLibraryDto update(UpdateResidualThicknessLibraryDto thicknessDto);

    ResponseResidualThicknessLibraryDto get(Long id);

    List<ResponseShortResidualThicknessLibraryDto> getAll(String name);

    void delete(Long id);
}