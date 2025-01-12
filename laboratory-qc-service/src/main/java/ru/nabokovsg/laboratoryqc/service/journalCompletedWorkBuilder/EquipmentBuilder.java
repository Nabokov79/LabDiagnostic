package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;


public interface EquipmentBuilder {

     void build(JournalCompletedWork journal, JournalType journalType);
}