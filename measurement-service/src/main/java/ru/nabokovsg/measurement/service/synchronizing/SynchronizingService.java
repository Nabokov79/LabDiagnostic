package ru.nabokovsg.measurement.service.synchronizing;

import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

public interface SynchronizingService {

    void updateDefectName(DefectLibrary defectLibrary);

    void updateRepairName(RepairLibrary repairLibrary);
}
