package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalCompletedWorkBuilderImpl implements JournalCompletedWorkBuilder {

    private final CompanyStructureBuilder structureBuilder;
    private final EquipmentBuilder equipmentBuilder;
    private final QCLEmployeeBuilder employeeBuilder;
    private final QCLDocumentBuilder documentBuilder;

    @Override
    public void build(JournalCompletedWork journal, JournalType journalType
                    , List<Long> employeesIds, Long documentTypeId) {
        structureBuilder.build(journal, journalType);
        equipmentBuilder.build(journal, journalType);
        employeeBuilder.build(journal, employeesIds);
        documentBuilder.build(journal, documentTypeId);
    }
}