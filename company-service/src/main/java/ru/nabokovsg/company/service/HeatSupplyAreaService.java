package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.heatSupplyArea.NewHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseShortHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.UpdateHeatSupplyAreaDto;

import java.util.List;

public interface HeatSupplyAreaService {

    ResponseShortHeatSupplyAreaDto save(NewHeatSupplyAreaDto areaDto);

    ResponseShortHeatSupplyAreaDto update(UpdateHeatSupplyAreaDto areaDto);

    ResponseHeatSupplyAreaDto get(Long id);

    List<ResponseShortHeatSupplyAreaDto> getAll(Long branchId, String name);

    void delete(Long id);
}