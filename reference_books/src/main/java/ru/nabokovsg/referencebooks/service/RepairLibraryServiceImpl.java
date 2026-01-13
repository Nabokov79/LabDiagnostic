package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.RepairLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RepairLibraryMapper;
import ru.nabokovsg.referencebooks.repository.RepairLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairLibraryServiceImpl implements RepairLibraryService {

    private final RepairLibraryRepository repository;
    private final RepairLibraryMapper mapper;
    private final MeasuredParameterLibraryService measuredParameterService;
    private final static String MASSAGE = "Ремонт не обнаружен.";

    @Override
    public ResponseRepairLibraryDto save(NewRepairLibraryDto repairDto) {
        validateByDuplicate(repairDto.getName());
        RepairLibrary repair = repository.save(mapper.mapToRepairLibrary(repairDto));
        return mapper.mapWithMeasurementParameter(repair, measuredParameterService.saveNewRepairParameter(repair, repairDto.getMeasuredParameters()));
    }

    @Override
    public ResponseRepairLibraryDto update(UpdateRepairLibraryDto repairDto) {
        if (repository.existsById(repairDto.getId())) {
            validateByDuplicate(repairDto.getName());
            RepairLibrary repair = repository.save(mapper.mapToUpdateRepairLibrary(repairDto));
            return mapper.mapWithMeasurementParameter(repair, measuredParameterService.update(repairDto.getMeasuredParameters()));
        }
        throw new NotFoundException(MASSAGE);
    }

    @Override
    public ResponseRepairLibraryDto get(Long id) {
        return mapper.mapToResponseRepairLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortRepairLibraryDto> getAll(String name) {
        if (name != null) {
            return repository.findAll().stream()
                    .filter(repair -> repair.getName().contains(name))
                    .map(mapper::mapToResponseShortRepairLibraryDto)
                    .toList();
        }
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponseShortRepairLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }

    @Override
    public RepairLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void validateByDuplicate(String name) {
        if (repository.existsByName(name)) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, name));
        }
    }
}