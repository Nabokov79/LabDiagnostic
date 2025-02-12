package ru.nabokovsg.equipment.service.equipment;

import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.model.equipment.StandardSize;

@Service
public class StandardSizeStringBuilderImpl implements StandardSizeStringBuilder {

    @Override
    public String convertToString(StandardSize standardSize) {
        if (standardSize.getThickness() != null) {
            return String.valueOf(standardSize.getThickness());
        }
        String standardSizeString = "";
        if (validByDiameter(standardSize.getMinDiameter(), standardSize.getMinThickness())) {
            standardSizeString = minDiameterToString(standardSize);
        }
        if (validByDiameter(standardSize.getMaxDiameter(), standardSize.getMaxThickness())) {
            standardSizeString = joinMaxDiameter(standardSizeString, standardSize);
        }
        return standardSizeString;
    }

    private boolean validByDiameter(Integer diameter, Double thickness) {
        if (diameter == null || thickness == null) {
            throw new BadRequestException(
                    String.format("unacceptable standard size: diameter=%s, thickness=%s", diameter, thickness));
        }
        if (diameter <= 0 || thickness <= 0) {
            throw new BadRequestException(
                    String.format("Diameter and thickness can only be positive: minDiameter=%s, thickness=%s", diameter, thickness));
        }
        return true;
    }

    private String minDiameterToString( StandardSize standardSize) {
        return String.join("x", String.valueOf(standardSize.getMinDiameter())
                , String.valueOf(standardSize.getMinThickness()));
    }

    private String joinMaxDiameter(String standardSizeString, StandardSize standardSize) {
        String diameter = String.join("x", String.valueOf(standardSize.getMaxDiameter())
                                                  , String.valueOf(standardSize.getMaxThickness()));
        if (standardSizeString.isBlank()) {
            return diameter;
        }
        return String.join("/", standardSizeString, diameter);
    }
}