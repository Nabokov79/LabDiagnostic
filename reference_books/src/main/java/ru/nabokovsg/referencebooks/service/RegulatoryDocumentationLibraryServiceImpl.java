package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseShortRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RegulatoryDocumentationLibraryMapper;
import ru.nabokovsg.referencebooks.repository.RegulatoryDocumentationLibraryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegulatoryDocumentationLibraryServiceImpl implements RegulatoryDocumentationLibraryService {

    private final RegulatoryDocumentationLibraryRepository repository;
    private final RegulatoryDocumentationLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final static String NOT_FOUND = "Документ не найден.";

    @Override
    public ResponseRegulatoryDocumentationLibraryDto save(NewRegulatoryDocumentationLibraryDto documentationDto) {
        exists(null, documentationDto.getDocument());
        RegulatoryDocumentationLibrary document = mapper.mapToRegulatoryDocumentation(documentationDto);
        setDocumentType(document);
        setDocumentStatus(document);
        document = setEquipments(document, documentationDto.getEquipmentIds());
        return mapper.mapToResponseRegulatoryDocumentationDto(repository.save(document));
    }

    @Override
    public ResponseRegulatoryDocumentationLibraryDto update(UpdateRegulatoryDocumentationLibraryDto documentationDto) {
        exists(documentationDto.getId(), documentationDto.getDocument());
        RegulatoryDocumentationLibrary document = getById(documentationDto.getId());
        mapper.mapToUpdateRegulatoryDocumentation(document, documentationDto);
        setDocumentType(document);
        setDocumentStatus(document);
        document = setEquipments(document, documentationDto.getEquipmentIds());
        return mapper.mapToResponseRegulatoryDocumentationDto(repository.save(document));
    }

    @Override
    public ResponseRegulatoryDocumentationLibraryDto get(Long id) {
        return mapper.mapToResponseRegulatoryDocumentationDto(getById(id));
    }

    @Override
    public List<ResponseShortRegulatoryDocumentationLibraryDto> getAll(String text) {
        Set<RegulatoryDocumentationLibrary> documentations = repository.findAllOrderByFullName();
        if (text != null) {
            String finalText = text.toLowerCase();
            documentations = documentations.stream()
                            .filter(document -> document.getFullName().toLowerCase().contains(finalText)
                                             || document.getDocument().toLowerCase().contains(finalText)
                                             || document.getDocumentName().toLowerCase().contains(finalText))
                            .collect(Collectors.toSet());
        }
        return documentations.stream()
                .map(mapper::mapToResponseShortRegulatoryDocumentationLibraryDto)
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

    @Override
    public RegulatoryDocumentationLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    @Override
    public String getDocument(Long id) {
        return repository.findDocumentById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    private void setDocumentType(RegulatoryDocumentationLibrary document) {
        RegulatoryDocumentationLibraryType type = RegulatoryDocumentationLibraryType.from(document.getDocumentType()).orElseThrow(
                () -> new BadRequestException(String.format("Тип документа не поддерживается: %s", document.getDocumentType())));
        mapper.mapWithType(document, type, type.label);
    }

    private void setDocumentStatus(RegulatoryDocumentationLibrary document) {
        RegulatoryDocumentationLibraryStatus status = RegulatoryDocumentationLibraryStatus.from(document.getDocumentStatus()).orElseThrow(
                () -> new BadRequestException(String.format("Статус документа не поддерживается: %s", document.getDocumentStatus())));
        mapper.mapWithStatus(document, status, status.label);
    }

    private RegulatoryDocumentationLibrary setEquipments(RegulatoryDocumentationLibrary document, List<Long> equipmentIds) {
        if (document.getEquipments() == null) {
            return mapper.mapWithEquipments(document, equipmentService.getAllByIds(equipmentIds));
        } else {
            document.getEquipments().forEach(equipment -> equipmentIds.remove(equipment.getId()));
            if (!equipmentIds.isEmpty()) {
                document.getEquipments().addAll(equipmentService.getAllByIds(equipmentIds));
            }
            return document;
        }
    }

    private void exists(Long id, String document) {
        boolean exists = false;
        if (id == null) {
            exists = repository.existsByDocument(document);
        } else {
            Long documentId = repository.findIdByDocument(document);
            if (documentId != null) {
                exists = !Objects.equals(id, documentId);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, document));
        }
    }
}