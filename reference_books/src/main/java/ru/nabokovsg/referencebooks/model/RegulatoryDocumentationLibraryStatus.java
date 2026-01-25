package ru.nabokovsg.referencebooks.model;

import java.util.Optional;

public enum RegulatoryDocumentationLibraryStatus {

    ACTIVE("действующий"),
    NO_ACTIVE("не действующий");

    public final String label;

    RegulatoryDocumentationLibraryStatus(String label) {
        this.label = label;
    }

    public static Optional<RegulatoryDocumentationLibraryStatus> from(String documentStatus) {
        for (RegulatoryDocumentationLibraryStatus status : values()) {
            if (status.name().equalsIgnoreCase(documentStatus)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}