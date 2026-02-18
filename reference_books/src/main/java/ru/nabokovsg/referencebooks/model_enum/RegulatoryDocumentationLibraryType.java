package ru.nabokovsg.referencebooks.model_enum;

import java.util.Optional;

public enum RegulatoryDocumentationLibraryType {

    GENERAL("Общий"),
    METHODICAL("Методический"),
    REGULATORY("Нормативный");

    public final String label;

    RegulatoryDocumentationLibraryType(String label) {
        this.label = label;
    }

    public static Optional<RegulatoryDocumentationLibraryType> from(String documentType) {
        for (RegulatoryDocumentationLibraryType type : values()) {
            if (type.name().equalsIgnoreCase(documentType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}