package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.qclEmployee.NewQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.ResponseQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.UpdateQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;

import java.util.List;

public interface QCLEmployeeService {

    ResponseQCLEmployeeDto save(NewQCLEmployeeDto employeeDto);

    ResponseQCLEmployeeDto update(UpdateQCLEmployeeDto employeeDto);

    ResponseQCLEmployeeDto get(Long id);

    List<ResponseQCLEmployeeDto> getAll();

    void delete(Long id);

    QCLEmployee getById(Long id);
}