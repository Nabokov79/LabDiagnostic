package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.NewQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.ResponseQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.UpdateQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

import java.util.List;

public interface QCLDocumentTypeService {

    ResponseQCLDocumentTypeDto save(NewQCLDocumentTypeDto documentTypeDto);

    ResponseQCLDocumentTypeDto update(UpdateQCLDocumentTypeDto documentTypeDto);

    ResponseQCLDocumentTypeDto get(Long id);

    QCLDocumentType getById(Long id);

    List<ResponseQCLDocumentTypeDto> getAll();

   void delete(Long id);
}