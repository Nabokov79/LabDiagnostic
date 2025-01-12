package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.NewQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.ResponseQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.UpdateQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QCLDocumentTypeMapper;
import ru.nabokovsg.laboratoryqc.repository.QCLDocumentTypeRepository;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QCLDocumentTypeServiceImpl implements QCLDocumentTypeService {

    private final QCLDocumentTypeRepository repository;
    private final QCLDocumentTypeMapper mapper;

    @Override
    public ResponseQCLDocumentTypeDto save(NewQCLDocumentTypeDto documentTypeDto) {
        if (repository.existsByDiagnosisTypeAndTypeDocumentAndDocumentTitle(documentTypeDto.getDiagnosisType()
                                                                          , documentTypeDto.getTypeDocument()
                                                                          , documentTypeDto.getDocumentTitle())) {
            throw new BadRequestException(
                    String.format("Diagnostic document type found : %s", documentTypeDto));
        }
        return mapper.mapToResponseQLCDocumentTypeDto(repository.save(mapper.mapToQLCDocumentType(documentTypeDto)));
    }

    @Override
    public ResponseQCLDocumentTypeDto update(UpdateQCLDocumentTypeDto documentTypeDto) {
        if (repository.existsById(documentTypeDto.getId())) {
            return mapper.mapToResponseQLCDocumentTypeDto(
                    repository.save(mapper.mapToUpdateQLCDocumentType(documentTypeDto)));
        }
        throw new NotFoundException(
                String.format("Diagnostic document type with id=%s was not found", documentTypeDto.getId()));
    }

    @Override
    public ResponseQCLDocumentTypeDto get(Long id) {
        return mapper.mapToResponseQLCDocumentTypeDto(getById(id));
    }

    @Override
    public QCLDocumentType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Diagnostic document type with id=%s not found", id)));
    }

    @Override
    public List<ResponseQCLDocumentTypeDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseQLCDocumentTypeDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Diagnostic document type with id = %s not found for delete", id));
    }
}