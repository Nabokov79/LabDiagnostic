package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

public interface QCLDocumentBuilder {

     void build(JournalCompletedWork journal, Long documentTypeId);
}