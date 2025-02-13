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
        String minSize = " ";
        if (standardSize.getMinDiameter() != null
                && validByDiameter(standardSize.getMinDiameter(), standardSize.getMinThickness())) {
            minSize = buildStandardSize(standardSize.getMinDiameter(), standardSize.getMinThickness());
        }
        if (standardSize.getMaxDiameter() != null
                && validByDiameter(standardSize.getMaxDiameter(), standardSize.getMaxThickness())) {
            String maxSize = buildStandardSize(standardSize.getMaxDiameter(), standardSize.getMaxThickness());
            if (minSize.isBlank()) {
                return maxSize;
            } else {
                minSize = String.join("/", minSize, maxSize);
            }
        }
        return minSize;
    }

    private boolean validByDiameter(Integer diameter, Double thickness) {
        if (diameter == null) {
            throw new BadRequestException(
                    String.format("Unacceptable standard size: diameter=%s, thickness=%s", diameter, thickness));
        }
        if (diameter <= 0) {
            throw new BadRequestException(String.format("Diameter can only be positive: %s", diameter));
        }
        if (thickness != null && thickness <= 0) {
            throw new BadRequestException(String.format("Thickness can only be positive: %s", thickness));
        }
        return true;
    }

    private String buildStandardSize(Integer diameter, Double thickness) {
        String diameterString = String.valueOf(diameter);
        if (thickness != null) {
            return String.join("x", diameterString, String.valueOf(thickness));
        }
        return diameterString;
    }
}