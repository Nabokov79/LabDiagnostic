package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;

import java.time.LocalDate;
import java.util.List;

public interface RequestJournalRecordsByPredicateService {

    List<JournalCompletedWork> getByPredicate(SearchDataJournalCompletedWork search, JournalType journalType);

    boolean exists(JournalType journalType, LocalDate date, Long equipmentId, Long addressId);
}