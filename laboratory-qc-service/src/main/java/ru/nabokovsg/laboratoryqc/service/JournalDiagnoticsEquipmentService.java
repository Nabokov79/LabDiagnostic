package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.NewJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.ResponseJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.UpdateJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;

import java.util.List;

public interface JournalDiagnoticsEquipmentService {

    ResponseJournalDiagnoticsEquipmentDto save(NewJournalDiagnoticsEquipmentDto journalDto);

    ResponseJournalDiagnoticsEquipmentDto update(UpdateJournalDiagnoticsEquipmentDto journalDto);

    ResponseJournalDiagnoticsEquipmentDto get(Long id);

   List<ResponseJournalDiagnoticsEquipmentDto> getAll(SearchDataJournalCompletedWork search);

    void delete(Long id);
}