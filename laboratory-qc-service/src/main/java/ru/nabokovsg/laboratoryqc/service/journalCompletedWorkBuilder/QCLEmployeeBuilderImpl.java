package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QCLEmployeeDataBuilderMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;
import ru.nabokovsg.laboratoryqc.repository.QCLEmployeeServiceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QCLEmployeeBuilderImpl implements QCLEmployeeBuilder {

    private final QCLEmployeeDataBuilderMapper mapper;
    private final QCLEmployeeServiceRepository repository;

    @Override
    public void build(JournalCompletedWork journal, List<Long> employeesIds) {
        Map<Long, QCLEmployee> qclEmployees = getQCLEmployee(employeesIds).stream()
                                                            .collect(Collectors.toMap(QCLEmployee::getId
                                                                                    , employee -> employee));
        QCLEmployee chief = repository.findByChief(true)
                                      .orElseThrow(() -> new NotFoundException("Laboratory chief not found"));
        mapper.mapToQCLEmployee(journal
                              , buildInitials(chief)
                              , chief
                              , getEmployees(qclEmployees)
                              , new HashSet<>(qclEmployees.values()));
    }

    private List<QCLEmployee> getQCLEmployee(List<Long> ids) {
        List<QCLEmployee> employees = repository.findAllById(ids);
        if (employees.size() != ids.size()) {
            throw new NotFoundException(String.format("QCL employees not found: %s", employees));
        }
        return employees;
    }

    private String buildInitials(QCLEmployee employee) {
        return String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                        , String.valueOf(employee.getPatronymic().charAt(0))).toUpperCase()
                , employee.getSurname());
    }

    private String getEmployees(Map<Long, QCLEmployee> qclEmployees) {
        return String.join("; ", qclEmployees.values().stream().map(this::buildInitials).toList());
    }
}