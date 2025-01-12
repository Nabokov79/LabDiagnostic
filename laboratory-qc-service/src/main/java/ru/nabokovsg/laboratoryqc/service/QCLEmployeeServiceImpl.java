package ru.nabokovsg.laboratoryqc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.NewQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.ResponseQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.UpdateQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.QCLEmployeeMapper;
import ru.nabokovsg.laboratoryqc.repository.QCLEmployeeServiceRepository;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QCLEmployeeServiceImpl implements QCLEmployeeService {

    private final QCLEmployeeServiceRepository repository;
    private final QCLEmployeeMapper mapper;

    @Override
    public ResponseQCLEmployeeDto save(NewQCLEmployeeDto employeeDto) {
        exists(employeeDto);
        return mapper.mapToResponseQCLEmployeeDto(repository.save(mapper.mapToQCLEmployee(employeeDto)));
    }

    @Override
    public ResponseQCLEmployeeDto update(UpdateQCLEmployeeDto employeeDto) {
        if (repository.existsById(employeeDto.getId())) {
            return mapper.mapToResponseQCLEmployeeDto(repository.save(mapper.mapToUpdateQCLEmployee(employeeDto)));
        }
       throw new NotFoundException(String.format("Employee with id=%s was not found", employeeDto.getId()));
    }

    @Override
    public ResponseQCLEmployeeDto get(Long id) {
        return mapper.mapToResponseQCLEmployeeDto(getById(id));
    }

    @Override
    public List<ResponseQCLEmployeeDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseQCLEmployeeDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Employee with id = %s not found for delete",id));
    }

    @Override
    public QCLEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id=%s was not found", id)));
    }

    private void exists(NewQCLEmployeeDto employeeDto) {
        if (employeeDto.isChief()) {
            if (repository.existsByChief(employeeDto.isChief())) {
                throw new BadRequestException(String.format("Laboratory chief found: %s", employeeDto));
            }
        }
        if (repository.existsByNameAndPatronymicAndSurnameAndEmail(employeeDto.getName(), employeeDto.getPatronymic()
                , employeeDto.getSurname(), employeeDto.getEmail())) {
            throw new BadRequestException(String.format("Laboratory employee found: %s", employeeDto));
        }
    }
}