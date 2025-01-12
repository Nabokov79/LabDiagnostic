package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

public interface RepairLibraryRepository extends JpaRepository<RepairLibrary, Long> {

    boolean existsByRepairName(String repairName);
}