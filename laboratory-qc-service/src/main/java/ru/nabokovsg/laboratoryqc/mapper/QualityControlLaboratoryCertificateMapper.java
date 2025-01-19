package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.DepartmentStructureDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.model.QualityControlLaboratoryCertificate;

@Mapper(componentModel = "spring")
public interface QualityControlLaboratoryCertificateMapper {

    QualityControlLaboratoryCertificate mapToQualityControlLaboratoryCertificate(
                                                            NewQualityControlLaboratoryCertificateDto certificateDto
                                                          , DepartmentStructureDto department);

    @Mapping(target = "id", ignore = true)
    void mapToUpdateQualityControlLaboratoryCertificate(
                                           @MappingTarget QualityControlLaboratoryCertificate certificate
                                                        , UpdateQualityControlLaboratoryCertificateDto certificateDto);

    ResponseQualityControlLaboratoryCertificateDto mapToResponseQualityControlLaboratoryCertificateDto(
                                                                    QualityControlLaboratoryCertificate certificate);
}