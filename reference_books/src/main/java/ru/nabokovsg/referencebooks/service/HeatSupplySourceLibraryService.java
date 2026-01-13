package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.NewHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseShortHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.UpdateHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;

import java.util.List;

public interface HeatSupplySourceLibraryService {

    ResponseShortHeatSupplySourceLibraryDto save(NewHeatSupplySourceLibraryDto sourceDto);

    ResponseShortHeatSupplySourceLibraryDto update(UpdateHeatSupplySourceLibraryDto sourceDto);

    ResponseHeatSupplySourceLibraryDto get(Long id);

    List<ResponseShortHeatSupplySourceLibraryDto> getAll(Long id, String source);

    void delete(Long id);

    HeatSupplySourceLibrary getById(Long id);
}