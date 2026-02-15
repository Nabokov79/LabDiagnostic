package ru.nabokovsg.referencebooks.search;

import ru.nabokovsg.referencebooks.model.DefectLibrary;

public interface DefectDuplicateSearchService {

    void exists(DefectLibrary defect, Long equipmentId, Long documentationId);
}