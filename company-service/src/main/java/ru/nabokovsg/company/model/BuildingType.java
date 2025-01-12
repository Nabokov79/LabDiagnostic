package ru.nabokovsg.company.model;

import java.util.Optional;

public enum BuildingType {

    BOILER_ROOM,
    CHP,
    IHP;

    public static Optional<BuildingType> from(String buildingType) {
        for (BuildingType type : values()) {
            if (type.name().equalsIgnoreCase(buildingType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}