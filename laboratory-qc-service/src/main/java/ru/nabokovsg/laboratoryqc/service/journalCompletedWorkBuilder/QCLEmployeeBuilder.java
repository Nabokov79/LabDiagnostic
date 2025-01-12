package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

import java.util.List;

public interface QCLEmployeeBuilder {

    void build(JournalCompletedWork journal, List<Long> employeesIds);
}