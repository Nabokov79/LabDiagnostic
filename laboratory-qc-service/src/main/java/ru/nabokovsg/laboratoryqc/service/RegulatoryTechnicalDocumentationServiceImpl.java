package ru.nabokovsg.laboratoryqc.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.NewRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.ResponseRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.UpdateRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.mapper.RegulatoryTechnicalDocumentationMapper;
import ru.nabokovsg.laboratoryqc.model.RegulatoryTechnicalDocumentation;
import ru.nabokovsg.laboratoryqc.repository.RegulatoryTechnicalDocumentationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegulatoryTechnicalDocumentationServiceImpl implements RegulatoryTechnicalDocumentationService {

    private final RegulatoryTechnicalDocumentationRepository repository;
    private final RegulatoryTechnicalDocumentationMapper mapper;

    @Override
    public ResponseRegulatoryTechnicalDocumentationDto save(NewRegulatoryTechnicalDocumentationDto documentationDto) {
        if (repository.existsByTitle(documentationDto.getTitle())) {
            throw new BadRequestException(
                    String.format("Regulatory technical document found : %s", documentationDto));
        }
        return mapper.mapToResponseRegulatoryTechnicalDocumentationDto(
                repository.save(mapper.mapToRegulatoryTechnicalDocumentation(documentationDto)));
    }

    @Override
    public ResponseRegulatoryTechnicalDocumentationDto update(UpdateRegulatoryTechnicalDocumentationDto documentationDto) {
        if (repository.existsById(documentationDto.getId())) {
            return mapper.mapToResponseRegulatoryTechnicalDocumentationDto(
                    repository.save(mapper.mapToUpdateRegulatoryTechnicalDocumentation(documentationDto)));
        }
        throw new NotFoundException(
                String.format("Regulatory technical document with id=%s not found for update", documentationDto.getId()));
    }

    @Override
    public ResponseRegulatoryTechnicalDocumentationDto get(Long id) {
        return mapper.mapToResponseRegulatoryTechnicalDocumentationDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("Regulatory technical document with id=%s not found", id)))
        );
    }

    @Override
    public List<ResponseRegulatoryTechnicalDocumentationDto> getAll(String text) {
        List<RegulatoryTechnicalDocumentation> documentations = repository.findAll();
        if (text != null) {
           documentations.forEach(document -> {
               if (!text.contains(document.getNumber()) && !text.contains(document.getTitle())) {
                   documentations.remove(document);
               }
           });
        }
        return documentations.stream()
                             .map(mapper::mapToResponseRegulatoryTechnicalDocumentationDto)
                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NotFoundException(
                String.format("Regulatory technical document with id=%s not found for delete", id));
    }
}