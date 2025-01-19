package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.client.ClientService;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.DepartmentStructureDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QualityControlLaboratoryCertificateMapper;
import ru.nabokovsg.laboratoryqc.model.QualityControlLaboratoryCertificate;
import ru.nabokovsg.laboratoryqc.repository.QualityControlLaboratoryCertificateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualityControlLaboratoryCertificateServiceImpl implements QualityControlLaboratoryCertificateService {

    private final QualityControlLaboratoryCertificateRepository repository;
    private final QualityControlLaboratoryCertificateMapper mapper;
    private final ClientService client;

    @Override
    public ResponseQualityControlLaboratoryCertificateDto save(NewQualityControlLaboratoryCertificateDto certificateDto) {
        if (repository.existsByDocumentNameAndDocumentNumberAndOrganization(certificateDto.getDocumentName()
                                                                          , certificateDto.getDocumentNumber()
                                                                          , certificateDto.getOrganization())) {
            throw new BadRequestException(String.format("Laboratory certificate found : %s", certificateDto));
        }

        DepartmentStructureDto department = client.getDepartmentStructure(certificateDto.getDepartmentId());
        return mapper.mapToResponseQualityControlLaboratoryCertificateDto(
                repository.save(mapper.mapToQualityControlLaboratoryCertificate(certificateDto, department)));
    }

    @Override
    public ResponseQualityControlLaboratoryCertificateDto update(UpdateQualityControlLaboratoryCertificateDto certificateDto) {
        QualityControlLaboratoryCertificate certificate = getById(certificateDto.getId());
        mapper.mapToUpdateQualityControlLaboratoryCertificate(certificate, certificateDto);
        return mapper.mapToResponseQualityControlLaboratoryCertificateDto(repository.save(certificate));
    }

    @Override
    public ResponseQualityControlLaboratoryCertificateDto get(Long id) {
        return mapper.mapToResponseQualityControlLaboratoryCertificateDto(getById(id));
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

    public QualityControlLaboratoryCertificate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Laboratory certificate with id=%s  not found", id)));
    }
}