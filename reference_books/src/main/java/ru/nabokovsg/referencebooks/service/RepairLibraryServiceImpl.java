package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.RepairLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RepairLibraryMapper;
import ru.nabokovsg.referencebooks.repository.RepairLibraryRepository;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.MeasurementParameterValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairLibraryServiceImpl implements RepairLibraryService {

    private final RepairLibraryRepository repository;
    private final RepairLibraryMapper mapper;
    private final MeasuredParameterLibraryService measuredParameterService;
    private final MeasurementParameterValidator parameterValidator;
    private final ToStringService toString;
    private final static String MASSAGE = "Ремонт не обнаружен.";

    @Override
    public ResponseShortRepairLibraryDto save(NewRepairLibraryDto repairDto) {
        List<MeasurementParameterLibrary> measuredParameters = measuredParameterService.create(repairDto.getMeasuredParametersLibrary());
        RepairLibrary repair = mapper.mapToRepairLibrary(repairDto);
        build(repair, measuredParameters);
        repair = repository.save(repair);
        measuredParameterService.saveRepairParameter(repair, measuredParameters);
        return mapper.mapToResponseShortRepairLibraryDto(repair);
    }

    @Override
    public ResponseShortRepairLibraryDto update(UpdateRepairLibraryDto repairDto) {
        RepairLibrary repair = getById(repairDto.getId());
        mapper.mapToUpdateRepairLibrary(repair, repairDto);
        measuredParameterService.update(repair.getMeasuredParametersLibrary(), repairDto.getMeasuredParametersLibrary());
        build(repair, repair.getMeasuredParametersLibrary());
        measuredParameterService.saveRepairParameter(repair, repair.getMeasuredParametersLibrary());
        return mapper.mapToResponseShortRepairLibraryDto(repository.save(repair));
    }

    @Override
    public ResponseRepairLibraryDto get(Long id) {
        return mapper.mapToResponseRepairLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortRepairLibraryDto> getAll(String name) {
        if (name != null) {
            String repairName = name.toLowerCase();
            return repository.findAll().stream()
                    .filter(repair -> repair.getName().toLowerCase().contains(repairName))
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

    private void build(RepairLibrary repair, List<MeasurementParameterLibrary> measuredParameters) {
        validate(repair, measuredParameters);
        exists(repair);
        mapper.mapWithMeasuredParameters(repair, toString.measuredParameters(measuredParameters));
    }

    private void exists(RepairLibrary repair) {
        boolean exists = false;
        if (repair.getId() == null) {
            exists = repository.existsByName(repair.getName());
        } else {
            Long id = repository.findIdByName(repair.getName());
            if (id != null) {
                exists = !repair.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, repair.getName()));
        }
    }

    private void validate(RepairLibrary repair, List<MeasurementParameterLibrary> measuredParameters) {
        parameterValidator.validateByQuantityMeasuredParameters(repair.getWithoutNamingParameter(), measuredParameters);
        parameterValidator.validateDuplicateMeasuredParameters(measuredParameters);
        measuredParameters.forEach(parameterValidator::validateAcceptableValue);
    }
}