package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.NewQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.ResponseQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.UpdateQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

@Mapper(componentModel = "spring")
public interface QCLDocumentTypeMapper {

    QCLDocumentType mapToQLCDocumentType(NewQCLDocumentTypeDto documentTypeDto);

    QCLDocumentType mapToUpdateQLCDocumentType(UpdateQCLDocumentTypeDto documentTypeDto);

    ResponseQCLDocumentTypeDto mapToResponseQLCDocumentTypeDto(QCLDocumentType documentType);
}