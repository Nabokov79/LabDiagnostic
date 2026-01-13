package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.NewHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseShortHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.UpdateHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.model.HeatSupplySiteLibrary;

import java.util.List;

public interface HeatSupplySiteLibraryService {

    ResponseShortHeatSupplySiteLibraryDto save(NewHeatSupplySiteLibraryDto siteDto);

    ResponseShortHeatSupplySiteLibraryDto update(UpdateHeatSupplySiteLibraryDto siteDto);

    ResponseHeatSupplySiteLibraryDto get(Long id);

    List<ResponseShortHeatSupplySiteLibraryDto> getAll(Long id, String name);

    void delete(Long id);

    HeatSupplySiteLibrary getById(Long id);
}