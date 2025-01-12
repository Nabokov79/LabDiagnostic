package ru.nabokovsg.company.service;

import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.model.BuildingType;

public class ConstantBuildingType {

    protected String get(String buildingType) {
        switch (convertToBuildingType(buildingType)) {
            case BOILER_ROOM -> {
                return "котельная";
            }
            case CHP -> {
                return "ЦТП";
            }
            case IHP -> {
                return "ИТП";
            }
            default -> throw new BadRequestException(
                    String.format("Unknown building type=%s", buildingType));
        }
    }

    private BuildingType convertToBuildingType(String buildingType) {
        return BuildingType.from(buildingType)
                .orElseThrow(
                       () -> new BadRequestException(String.format("Unknown data format buildingType=%s", buildingType))
                );
    }
}
