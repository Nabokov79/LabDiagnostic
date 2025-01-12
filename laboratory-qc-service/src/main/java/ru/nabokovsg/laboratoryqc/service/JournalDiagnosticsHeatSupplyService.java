package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.NewJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.ResponseJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.UpdateJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;

import java.util.List;

public interface JournalDiagnosticsHeatSupplyService {

    ResponseJournalDiagnosticsHeatSupplyDto save(NewJournalDiagnosticsHeatSupplyDto journalDto);

    ResponseJournalDiagnosticsHeatSupplyDto update(UpdateJournalDiagnosticsHeatSupplyDto journalDto);

    ResponseJournalDiagnosticsHeatSupplyDto get(Long id);

   List<ResponseJournalDiagnosticsHeatSupplyDto> getAll(SearchDataJournalCompletedWork search);

    void delete(Long id);
}