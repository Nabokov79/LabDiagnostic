package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.NewDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.ResponseDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.UpdateDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DiagnosisDocumentLibraryMapper;
import ru.nabokovsg.referencebooks.model.DiagnosisDocumentLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.DiagnosisDocumentLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisDocumentLibraryServiceImpl implements DiagnosisDocumentLibraryService {

    private final DiagnosisDocumentLibraryRepository repository;
    private final DiagnosisDocumentLibraryMapper mapper;
    private final static String NOT_FOUND = "Документ не найден.";

    @Override
    public ResponseDiagnosisDocumentLibraryDto save(NewDiagnosisDocumentLibraryDto documentDto) {
        DiagnosisDocumentLibrary document = mapper.mapToDiagnosisDocumentLibrary(documentDto);
        exists(document);
        return mapper.mapToResponseDiagnosisDocumentLibraryDto(repository.save(document));
    }

    @Override
    public ResponseDiagnosisDocumentLibraryDto update(UpdateDiagnosisDocumentLibraryDto documentDto) {
        DiagnosisDocumentLibrary document = getById(documentDto.getId());
        mapper.mapTuUpdateDiagnosisDocumentLibrary(document, documentDto);
        exists(document);
        return mapper.mapToResponseDiagnosisDocumentLibraryDto(repository.save(document));
    }

    @Override
    public ResponseDiagnosisDocumentLibraryDto get(Long id) {
        return mapper.mapToResponseDiagnosisDocumentLibraryDto(getById(id));
    }

    @Override
    public List<ResponseDiagnosisDocumentLibraryDto> getAll(String name) {
        Set<DiagnosisDocumentLibrary> documents = repository.findAllOrderByDocument();
        if (name != null) {
            final String documentName = name.toLowerCase();
            documents = documents.stream()
                                 .filter(document -> document.getDocument().toLowerCase().contains(documentName))
                                 .collect(Collectors.toSet());
        }
        return documents.stream()
                        .map(mapper::mapToResponseDiagnosisDocumentLibraryDto)
                        .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NOT_FOUND);
    }

    private DiagnosisDocumentLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    private void exists(DiagnosisDocumentLibrary document) {
        boolean exists = false;
        if (document.getId() == null) {
            exists = repository.existsByDocument(document.getDocument());
        } else {
            Long id = repository.findIdByDocument(document.getDocument());
            if (id != null) {
                exists = !document.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, document.getDocument()));
        }
    }
}