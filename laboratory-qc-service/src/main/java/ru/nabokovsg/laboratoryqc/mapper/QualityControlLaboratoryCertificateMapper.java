package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.model.QualityControlLaboratoryCertificate;

@Mapper(componentModel = "spring")
public interface QualityControlLaboratoryCertificateMapper {

    QualityControlLaboratoryCertificate mapToQualityControlLaboratoryCertificate(
                                                            NewQualityControlLaboratoryCertificateDto certificateDto);

    QualityControlLaboratoryCertificate mapToUpdateQualityControlLaboratoryCertificate(
                                                        UpdateQualityControlLaboratoryCertificateDto certificateDto);

    ResponseQualityControlLaboratoryCertificateDto mapToResponseQualityControlLaboratoryCertificateDto(
                                                                    QualityControlLaboratoryCertificate certificate);
}