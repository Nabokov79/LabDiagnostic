package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface QCLEmployeeDataBuilderMapper {

    @Mapping(source = "chief", target = "chief")
    @Mapping(source = "employees", target = "employees")
    @Mapping(source = "headWorkPerformer", target = "headWorkPerformer")
    @Mapping(source = "workPerformers", target = "workPerformers")
    void mapToQCLEmployee(@MappingTarget JournalCompletedWork journal
                                        , String chief
                                        , QCLEmployee headWorkPerformer
                                        , String employees
                                        , Set<QCLEmployee> workPerformers);
}