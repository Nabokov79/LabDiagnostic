package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QualityControlLaboratoryCertificateMapper;
import ru.nabokovsg.laboratoryqc.repository.QualityControlLaboratoryCertificateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualityControlLaboratoryCertificateServiceImpl implements QualityControlLaboratoryCertificateService {

    private final QualityControlLaboratoryCertificateRepository repository;
    private final QualityControlLaboratoryCertificateMapper mapper;

    @Override
    public ResponseQualityControlLaboratoryCertificateDto save(NewQualityControlLaboratoryCertificateDto certificateDto) {
        if (repository.existsByDocumentNameAndDocumentNumberAndOrganization(certificateDto.getDocumentName()
                                                                          , certificateDto.getDocumentNumber()
                                                                          , certificateDto.getOrganization())) {
            throw new BadRequestException(String.format("Laboratory certificate found : %s", certificateDto));
        }
        return mapper.mapToResponseQualityControlLaboratoryCertificateDto(
                repository.save(mapper.mapToQualityControlLaboratoryCertificate(certificateDto)));
    }

    @Override
    public ResponseQualityControlLaboratoryCertificateDto update(UpdateQualityControlLaboratoryCertificateDto certificateDto) {
        if (repository.existsById(certificateDto.getId())) {
            return mapper.mapToResponseQualityControlLaboratoryCertificateDto(
                    repository.save(mapper.mapToUpdateQualityControlLaboratoryCertificate(certificateDto)));
        }
        throw new NotFoundException(
                String.format("Laboratory certificate with id = %s not found for update", certificateDto.getId()));
    }

    @Override
    public ResponseQualityControlLaboratoryCertificateDto get(Long id) {
        return mapper.mapToResponseQualityControlLaboratoryCertificateDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Laboratory certificate with id=%s  not found", id))));
    }

    @Override
    public List<ResponseQualityControlLaboratoryCertificateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseQualityControlLaboratoryCertificateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Laboratory certificate with id = %s not found for delete", id));
    }
}