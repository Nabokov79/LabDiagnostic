package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.NewQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.ResponseQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.UpdateQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;

@Mapper(componentModel = "spring")
public interface QCLEmployeeMapper {

    QCLEmployee mapToQCLEmployee(NewQCLEmployeeDto employeeDto);

    QCLEmployee mapToUpdateQCLEmployee(UpdateQCLEmployeeDto employeeDto);

    ResponseQCLEmployeeDto mapToResponseQCLEmployeeDto(QCLEmployee employee);
}