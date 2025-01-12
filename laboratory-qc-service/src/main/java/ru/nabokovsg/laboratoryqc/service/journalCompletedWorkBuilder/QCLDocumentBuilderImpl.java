package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.mapper.QCLDocumentBuilderMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;
import ru.nabokovsg.laboratoryqc.model.QJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.service.QCLDocumentTypeService;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
public class QCLDocumentBuilderImpl implements QCLDocumentBuilder {

    private final QCLDocumentBuilderMapper mapper;
    private final QCLDocumentTypeService documentTypeService;
    private final EntityManager em;

    @Override
    public void build(JournalCompletedWork journal, Long documentTypeId) {
        QCLDocumentType documentType = documentTypeService.getById(documentTypeId);
        String document = String.join(" ", documentType.getType(), documentType.getDocumentTitle());
        mapper.mapToDocument(journal, documentType, document, getDocumentNumber(journal));
    }

    private Integer getDocumentNumber(JournalCompletedWork journal) {
        if (journal.getDate() == null) {
            return null;
        }
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());
        QJournalCompletedWork journalCompletedWork = QJournalCompletedWork.journalCompletedWork;
        Integer number = new JPAQueryFactory(em).from(journalCompletedWork)
                .select(journalCompletedWork.documentNumber.max())
                .where(journalCompletedWork.date.after(firstDay).and(journalCompletedWork.date.before(lastDay)))
                .fetchOne();
        if (number == null) {
            return 1;
        }
        return (++number);
    }
}