package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.NewQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.ResponseQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.UpdateQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QCLEmployeeCertificateMapper;
import ru.nabokovsg.laboratoryqc.repository.QCLEmployeeCertificateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QCLEmployeeCertificateServiceImpl implements QCLEmployeeCertificateService {

    private final QCLEmployeeCertificateRepository repository;
    private final QCLEmployeeCertificateMapper mapper;
    private final QCLEmployeeService employeeService;

    @Override
    public ResponseQCLEmployeeCertificateDto save(NewQCLEmployeeCertificateDto certificateDto) {
        if (repository.existsByControlNameAndEmployeeId(certificateDto.getControlName()
                                                      , certificateDto.getEmployeeId())) {
            throw new NotFoundException(
                    String.format("Employee certificate found : %s", certificateDto));
        }
        return mapper.mapToResponseQCLEmployeeCertificateDto(
                repository.save(mapper.mapToQCLEmployeeCertificate(certificateDto
                                                            , employeeService.getById(certificateDto.getEmployeeId())))
        );
    }

    @Override
    public ResponseQCLEmployeeCertificateDto update(UpdateQCLEmployeeCertificateDto certificateDto) {
        if(repository.existsById(certificateDto.getId())) {
            return mapper.mapToResponseQCLEmployeeCertificateDto(
                    repository.save(mapper.mapToUpdateQCLEmployeeCertificate(certificateDto
                                                             , employeeService.getById(certificateDto.getEmployeeId())))
            );
        }
        throw new NotFoundException(
                String.format("Employee certificate with id=%s not found for update", certificateDto.getId()));
    }

    @Override
    public ResponseQCLEmployeeCertificateDto get(Long id) {
        return mapper.mapToResponseQCLEmployeeCertificateDto(
                repository.findById(id)
                          .orElseThrow(() -> new NotFoundException(
                                  String.format("Employee certificate with id=%s not found", id))));
    }

    @Override
    public List<ResponseQCLEmployeeCertificateDto> getAll(Long employeeId) {
        return repository.findAllByEmployeeId(employeeId)
                         .stream()
                         .map(mapper::mapToResponseQCLEmployeeCertificateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new NotFoundException(
                String.format("Employee certificate with id=%s not found for delete", id));
    }
}