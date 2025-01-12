package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.NewQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.ResponseQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.UpdateQCLEmployeeCertificateDto;

import java.util.List;

public interface QCLEmployeeCertificateService {

    ResponseQCLEmployeeCertificateDto save(NewQCLEmployeeCertificateDto certificateDto);

    ResponseQCLEmployeeCertificateDto update(UpdateQCLEmployeeCertificateDto certificateDto);

    ResponseQCLEmployeeCertificateDto get(Long id);

   List<ResponseQCLEmployeeCertificateDto> getAll(Long employeeId);

   void delete(Long id);
}