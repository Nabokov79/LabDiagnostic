package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.NewQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.ResponseQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.UpdateQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

@Mapper(componentModel = "spring")
public interface QualityControlJournalMapper {

    ResponseQualityControlJournalDto mapToResponseJournalDiagnoticsEquipmentDto(JournalCompletedWork record);

    JournalCompletedWork mapToJournalCompletedWork(NewQualityControlJournalDto journalDto
                                                 , String journalType
                                                 , String measuringInstruments);

    JournalCompletedWork mapToUpdateJournalCompletedWork(UpdateQualityControlJournalDto journalDto
                                                       , String journalType
                                                       , String measuringInstruments);
}
