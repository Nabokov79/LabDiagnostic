package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;

import java.util.List;

public interface QualityControlLaboratoryCertificateService {

    ResponseQualityControlLaboratoryCertificateDto save(NewQualityControlLaboratoryCertificateDto certificateDto);

    ResponseQualityControlLaboratoryCertificateDto update(UpdateQualityControlLaboratoryCertificateDto certificateDto);

    ResponseQualityControlLaboratoryCertificateDto get(Long id);

    List<ResponseQualityControlLaboratoryCertificateDto> getAll();

    void delete(Long id);
}