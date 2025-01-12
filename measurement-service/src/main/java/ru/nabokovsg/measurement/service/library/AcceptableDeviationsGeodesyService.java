package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.NewAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.UpdateAcceptableDeviationsGeodesyDto;

import java.util.List;

public interface AcceptableDeviationsGeodesyService {

    ResponseAcceptableDeviationsGeodesyDto save(NewAcceptableDeviationsGeodesyDto geodesyDto);

    ResponseAcceptableDeviationsGeodesyDto update(UpdateAcceptableDeviationsGeodesyDto geodesyDto);

    ResponseAcceptableDeviationsGeodesyDto get(Long id);

    List<ResponseAcceptableDeviationsGeodesyDto> getAll(Long equipmentLibraryId);

    void delete(Long id);
}