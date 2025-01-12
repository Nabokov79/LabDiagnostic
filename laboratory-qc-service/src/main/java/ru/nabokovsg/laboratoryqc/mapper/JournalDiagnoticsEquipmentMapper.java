package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.NewJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.ResponseJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.UpdateJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

@Mapper(componentModel = "spring")
public interface JournalDiagnoticsEquipmentMapper {

    JournalCompletedWork mapToJournalCompletedWork(NewJournalDiagnoticsEquipmentDto journalDto, String journalType);

    JournalCompletedWork mapToUpdateJournalCompletedWork(UpdateJournalDiagnoticsEquipmentDto journalDto, String journalType);

    ResponseJournalDiagnoticsEquipmentDto mapToResponseJournalDiagnoticsEquipmentDto(JournalCompletedWork record);
}