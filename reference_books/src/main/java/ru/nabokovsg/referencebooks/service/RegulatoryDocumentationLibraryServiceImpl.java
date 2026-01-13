package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RegulatoryDocumentationLibraryMapper;
import ru.nabokovsg.referencebooks.repository.RegulatoryDocumentationLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegulatoryDocumentationLibraryServiceImpl implements RegulatoryDocumentationLibraryService {

    private final RegulatoryDocumentationLibraryRepository repository;
    private final RegulatoryDocumentationLibraryMapper mapper;

    @Override
    public ResponseRegulatoryDocumentationLibraryDto save(NewRegulatoryDocumentationLibraryDto documentationDto) {
        if (repository.existsByViewAndNumber(documentationDto.getView(), documentationDto.getNumber())) {
            throw new BadRequestException(
                    String.format("Regulatory document found : %s", documentationDto));
        }
        return mapper.mapToResponseRegulatoryDocumentationDto(
                repository.save(mapper.mapToRegulatoryDocumentation(documentationDto)));
    }

    @Override
    public ResponseRegulatoryDocumentationLibraryDto update(UpdateRegulatoryDocumentationLibraryDto documentationDto) {
        if (repository.existsById(documentationDto.getId())) {
            return mapper.mapToResponseRegulatoryDocumentationDto(
                    repository.save(mapper.mapToUpdateRegulatoryDocumentation(documentationDto)));
        }
        throw new NotFoundException(
                String.format("Regulatory document with id=%s not found for update", documentationDto.getId()));
    }

    @Override
    public ResponseRegulatoryDocumentationLibraryDto get(Long id) {
        return mapper.mapToResponseRegulatoryDocumentationDto(getById(id));
    }

    @Override
    public List<ResponseRegulatoryDocumentationLibraryDto> getAll(String text) {
        if (text != null) {
            String finalText = text.toLowerCase();
            return repository.findAllOrderByNumber().stream()
                    .filter(document -> document.getView().toLowerCase().contains(finalText)
                            || document.getNumber().toLowerCase().contains(finalText)
                            || document.getTitle().toLowerCase().contains(finalText))
                    .map(mapper::mapToResponseRegulatoryDocumentationDto)
                    .toList();
        }
        return repository.findAllOrderByView().stream()
                .map(mapper::mapToResponseRegulatoryDocumentationDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                String.format("Regulatory document with id=%s not found for delete", id));
    }

    @Override
    public RegulatoryDocumentationLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Regulatory document with id=%s not found", id)));
    }
}