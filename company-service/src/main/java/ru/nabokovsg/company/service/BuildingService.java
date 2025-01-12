package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.building.NewBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseShortBuildingDto;
import ru.nabokovsg.company.dto.building.UpdateBuildingDto;

import java.util.List;

public interface BuildingService {

    ResponseShortBuildingDto save(NewBuildingDto buildingDto);

    ResponseShortBuildingDto update(UpdateBuildingDto buildingDto);

    ResponseBuildingDto get(Long id);

    List<ResponseShortBuildingDto> getAll(Long id);

    void  delete(Long id);
}