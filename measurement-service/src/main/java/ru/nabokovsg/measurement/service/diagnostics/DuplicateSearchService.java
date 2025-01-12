package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.Set;

public interface DuplicateSearchService {

    DefectMeasurement searchDefectDuplicate(DefectMeasurement identifiedDefect
                                          , Set<DefectMeasurement> identifiedDefects);

    RepairMeasurement searchRepairDuplicate(RepairMeasurement completedRepair
                                          , Set<RepairMeasurement> completedRepairs);
}