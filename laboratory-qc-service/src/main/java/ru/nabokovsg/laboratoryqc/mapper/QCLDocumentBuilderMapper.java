package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

@Mapper(componentModel = "spring")
public interface QCLDocumentBuilderMapper {

    void mapToDocument(@MappingTarget JournalCompletedWork journal
                                    , QCLDocumentType documentType
                                    , String document
                                    , Integer documentNumber);
}