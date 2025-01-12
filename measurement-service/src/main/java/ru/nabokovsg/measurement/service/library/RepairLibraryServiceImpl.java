package ru.nabokovsg.measurement.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.RepairLibraryMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.model.library.RepairLibrary;
import ru.nabokovsg.measurement.model.library.TypeMeasuredParameterBuilder;
import ru.nabokovsg.measurement.repository.library.RepairLibraryRepository;
import ru.nabokovsg.measurement.service.synchronizing.SynchronizingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairLibraryServiceImpl implements RepairLibraryService {

    private final RepairLibraryRepository repository;
    private final RepairLibraryMapper mapper;
    private final MeasuredParameterLibraryService parameterService;
    private final SynchronizingService synchronizingService;

    @Override
    public ResponseRepairLibraryDto save(NewRepairLibraryDto repairDto) {
        if (repository.existsByRepairName(repairDto.getRepairName())) {
            throw new BadRequestException(
                    String.format("RepairLibrary with name=%s is found", repairDto.getRepairName()));
        }
        RepairLibrary repair = mapper.mapToTypeRepairLibrary(repairDto);
        addTypeCalculation(repair, repairDto.getCalculation());
        repair = repository.save(repair);
        repair.setMeasuredParameters(parameterService.save(new TypeMeasuredParameterBuilder.Builder()
                        .libraryDataType(MeasurementType.REPAIR)
                        .calculation(repair.getCalculation())
                        .repair(repair)
                        .build()
                , repairDto.getMeasuredParameters()));
        return mapper.mapToResponseTypeRepairLibraryDto(repair);
    }

    @Override
    public ResponseRepairLibraryDto update(UpdateRepairLibraryDto repairDto) {
        RepairLibrary repair = getById(repairDto.getId());
        mapper.mapToUpdateTypeRepairLibrary(repair, repairDto);
        addTypeCalculation(repair, repairDto.getCalculation());
        repair = repository.save(repair);
        repair.setMeasuredParameters(parameterService.update(repair.getMeasuredParameters()
                , repairDto.getMeasuredParameters()));
        synchronizingService.updateRepairName(repair);
        return mapper.mapToResponseTypeRepairLibraryDto(repair);
    }

    @Override
    public ResponseRepairLibraryDto get(Long id) {
        return mapper.mapToResponseTypeRepairLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortRepairLibraryDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseShortTypeRepairLibraryDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("RepairLibrary with id=%s not found for delete", id));
    }

    private void addTypeCalculation(RepairLibrary repair, String calculation) {
        ParameterCalculationType calculationType = ParameterCalculationType.from(calculation).orElseThrow(
                () -> new BadRequestException(String.format("Unsupported calculation type=%s", calculation)));
        mapper.mapWithParameterCalculationType(repair, calculationType);
    }

    @Override
    public RepairLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("RepairLibrary with id=%s not found", id)));
    }
}