package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.NewJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.ResponseJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply.UpdateJournalDiagnosticsHeatSupplyDto;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

@Mapper(componentModel = "spring")
public interface JournalDiagnosticsHeatSupplyMapper {

    JournalCompletedWork mapToJournalCompletedWork(NewJournalDiagnosticsHeatSupplyDto journalDto, String journalType);

    JournalCompletedWork mapToUpdateJournalCompletedWork(UpdateJournalDiagnosticsHeatSupplyDto journalDto, String journalType);

    ResponseJournalDiagnosticsHeatSupplyDto mapToResponseJournalDiagnoticsEquipmentDto(JournalCompletedWork record);
}