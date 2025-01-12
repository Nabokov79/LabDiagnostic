package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;

import java.util.List;

public interface JournalCompletedWorkBuilder {

    void build(JournalCompletedWork journal, JournalType journalType, List<Long> employeesIds, Long documentTypeId);
}
