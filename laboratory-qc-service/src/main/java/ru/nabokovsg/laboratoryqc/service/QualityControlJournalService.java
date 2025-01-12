package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.NewQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.ResponseQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.UpdateQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;

import java.util.List;

public interface QualityControlJournalService {

    ResponseQualityControlJournalDto save(NewQualityControlJournalDto journalDto);

    ResponseQualityControlJournalDto update(UpdateQualityControlJournalDto journalDto);

    ResponseQualityControlJournalDto get(Long id);

    List<ResponseQualityControlJournalDto> getAll(SearchDataJournalCompletedWork search);

    void delete(Long id);
}