package ru.nabokovsg.equipment.service.equipment;

import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.model.equipment.StandardSize;

@Service
public class StandardSizeStringBuilderImpl implements StandardSizeStringBuilder {

    @Override
    public String convertToString(StandardSize standardSize) {
        validate(standardSize);
        String standardSizeString = "";
        if (standardSize.getThickness() != null) {
            standardSizeString = thicknessToString(standardSize.getThickness());
            return standardSizeString;
        }
        if (standardSize.getMinDiameter() != null) {
            standardSizeString = minDiameterToString(standardSize.getMinDiameter());
            standardSizeString = joinMinThickness(standardSizeString, standardSize.getMinThickness());
        }
        if (standardSize.getMaxDiameter() != null) {
            standardSizeString = joinMaxDiameter(standardSizeString, standardSize.getMaxDiameter());
            standardSizeString = joinMaxThickness(standardSizeString, standardSize.getMaxThickness());
        }
        return standardSizeString;
    }

    private void validate(StandardSize standardSize) {
        String message = String.format(": thickness=%s, maxDiameter=%s, minDiameter=%s"
                                        , standardSize.getThickness()
                                        , standardSize.getMaxDiameter()
                                        , standardSize.getMinDiameter());
        if (standardSize.getThickness() == null && standardSize.getMinDiameter() == null
                                                && standardSize.getMaxDiameter() == null) {
            message = String.join("", "StandardSize should not be null", message);
            throw new BadRequestException(message);
        }
        if (standardSize.getThickness() != null && (standardSize.getMinDiameter() != null
                                                || standardSize.getMaxDiameter() != null)) {
            message = String.join("", "Invalid standard size", message);
            throw new BadRequestException(message);
        }
    }

    private String thicknessToString(Double thickness) {
        if (thickness <= 0 ) {
            throw new BadRequestException(
                 String.format("MinThickness should not be null and can only be positive: minThickness=%s", thickness));
        }
        return String.valueOf(thickness);
    }

    private String minDiameterToString(Integer minDiameter) {
        if (minDiameter <= 0 ) {
            throw new BadRequestException(
                 String.format("MinDiameter can only be positive: minDiameter=%s", minDiameter));
        }
        return String.valueOf(minDiameter);
    }

    private String joinMinThickness(String standardSizeString, Double minThickness) {
        if (minThickness != null) {
            if (minThickness <= 0 ) {
                throw new BadRequestException(
                        String.format("MinThickness should not be null and can only be positive: minThickness=%s", minThickness));
            }
            return String.join("x", standardSizeString, String.valueOf(minThickness));
        }
        return standardSizeString;
    }

    private String joinMaxDiameter(String standardSizeString, Integer maxDiameter) {
        if (maxDiameter != null && maxDiameter <= 0 ) {
            throw new BadRequestException(
                 String.format("MaxDiameter should not be null and can only be positive: maxDiameter=%s", maxDiameter));
        }
        String diameter = String.valueOf(maxDiameter);
        if (standardSizeString.isBlank()) {
            return diameter;
        }
        return String.join("/", standardSizeString, diameter);
    }

    private String joinMaxThickness(String standardSizeString, Double maxThickness) {
        if (maxThickness != null) {
            if (maxThickness <= 0 ) {
                throw new BadRequestException(
                        String.format("MaxThickness should not be null and can only be positive: maxThickness=%s", maxThickness));
            }
            return String.join("x", standardSizeString, String.valueOf(maxThickness));
        }
        return standardSizeString;
    }
}