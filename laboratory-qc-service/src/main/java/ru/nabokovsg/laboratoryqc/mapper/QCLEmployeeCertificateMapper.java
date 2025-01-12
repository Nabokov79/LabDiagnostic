package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.NewQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.ResponseQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.UpdateQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;
import ru.nabokovsg.laboratoryqc.model.QCLEmployeeCertificate;

@Mapper(componentModel = "spring")
public interface QCLEmployeeCertificateMapper {

    @Mapping(target = "id",ignore = true)
    QCLEmployeeCertificate mapToQCLEmployeeCertificate(NewQCLEmployeeCertificateDto certificateDto
                                                     , QCLEmployee employee);
    @Mapping(source = "certificateDto.id", target = "id")
    QCLEmployeeCertificate mapToUpdateQCLEmployeeCertificate(UpdateQCLEmployeeCertificateDto certificateDto
                                                           , QCLEmployee employee);

    ResponseQCLEmployeeCertificateDto mapToResponseQCLEmployeeCertificateDto(QCLEmployeeCertificate certificate);
}