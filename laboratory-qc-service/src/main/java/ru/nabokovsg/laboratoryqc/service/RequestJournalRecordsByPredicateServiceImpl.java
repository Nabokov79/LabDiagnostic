package ru.nabokovsg.laboratoryqc.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.model.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestJournalRecordsByPredicateServiceImpl implements RequestJournalRecordsByPredicateService {

    private final EntityManager em;

    @Override
    public List<JournalCompletedWork> getByPredicate(SearchDataJournalCompletedWork search, JournalType journalType) {
        QJournalCompletedWork record = QJournalCompletedWork.journalCompletedWork;
        QQCLEmployee employee = QQCLEmployee.qCLEmployee;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(record.journalType.eq(journalType.label));
        switch (journalType) {
            case EQUIPMENT, HEAT_SUPPLY : {
                addPredicateByEmployee(search, builder, employee);
                addPredicateByDate(search, record, builder);
                addPredicateByAddress(search, record, builder);
                addPredicateByEquipmentLibrary(search, record, builder);
            }
            case QUALITY_CONTROL : {
                addPredicateByEmployee(search, builder, employee);
                addPredicateByDate(search, record, builder);
            }
        }
        return new JPAQueryFactory(em).from(record)
                                      .select(record)
                                      .leftJoin(record.workPerformers, employee)
                                      .where(builder)
                                      .orderBy(record.date.asc())
                                      .fetch();
    }

    @Override
    public boolean exists(JournalType journalType, LocalDate date, Long equipmentId, Long addressId) {
        QJournalCompletedWork record = QJournalCompletedWork.journalCompletedWork;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(record.journalType.eq(journalType.label));
        builder.and(record.equipmentId.eq(equipmentId));
        builder.and(record.addressId.eq(addressId));
        if (date != null) {
            builder.and(record.date.eq(date));
        }
        return new JPAQueryFactory(em).from(record)
                .select(record)
                .where(builder)
                .fetchOne() == null;
    }

    private void addPredicateByEmployee(SearchDataJournalCompletedWork search
                                      , BooleanBuilder builder
                                      , QQCLEmployee employee) {
        if (search.getEmployeesId() != null) {
            builder.and(employee.id.eq(search.getEmployeesId()));
        }
    }

    private void addPredicateByDate(SearchDataJournalCompletedWork search
                                  , QJournalCompletedWork record
                                  , BooleanBuilder builder) {
        LocalDate startPeriod = search.getStartPeriod();
        LocalDate endPeriod = search.getEndPeriod();
        if (startPeriod == null && endPeriod == null) {
            LocalDate date = LocalDate.now();
            startPeriod = date.with(DayOfWeek.MONDAY).minusDays(1);
            endPeriod = date.with(DayOfWeek.SUNDAY).plusDays(8);

        }
        if (startPeriod != null) {
            builder.and(record.date.after(startPeriod));
        }
        if (endPeriod != null) {
            builder.and(record.date.before(endPeriod));
        }
    }

    private void addPredicateByAddress(SearchDataJournalCompletedWork search
                                     , QJournalCompletedWork record
                                     , BooleanBuilder builder) {
        if (search.getAddressId() != null) {
            builder.and(record.addressId.eq(search.getAddressId()));
        }
    }

    private void addPredicateByEquipmentLibrary(SearchDataJournalCompletedWork search
                                              , QJournalCompletedWork record
                                              , BooleanBuilder builder) {
        if (search.getEquipmentId() != null) {
            builder.and(record.equipmentId.eq(search.getEquipmentId()));
        }
    }
}