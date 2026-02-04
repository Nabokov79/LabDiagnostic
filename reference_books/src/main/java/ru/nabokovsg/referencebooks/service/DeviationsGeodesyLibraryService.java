package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.NewDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.ResponseDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.UpdateDeviationsGeodesyLibraryDto;

import java.util.List;

public interface DeviationsGeodesyLibraryService {

    ResponseDeviationsGeodesyLibraryDto save(NewDeviationsGeodesyLibraryDto geodesyDto);

    ResponseDeviationsGeodesyLibraryDto update(UpdateDeviationsGeodesyLibraryDto geodesyDto);

    ResponseDeviationsGeodesyLibraryDto get(Long id);

    List<ResponseDeviationsGeodesyLibraryDto> getAll(String name);

    void delete(Long id);
}