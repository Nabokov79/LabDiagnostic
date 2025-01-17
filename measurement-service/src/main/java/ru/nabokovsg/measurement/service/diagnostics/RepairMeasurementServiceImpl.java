package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.repair.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.UpdateRepairMeasurementDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.RepairMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.RepairMeasurementRepository;
import ru.nabokovsg.measurement.service.library.RepairLibraryService;
import ru.nabokovsg.measurement.сalculation.CalculationRepairMeasurementService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RepairMeasurementServiceImpl implements RepairMeasurementService {

    private final RepairMeasurementRepository repository;
    private final RepairMeasurementMapper mapper;
    private final RepairLibraryService libraryService;
    private final MeasuredParameterService measuredParameterService;
    private final DuplicateSearchService duplicateSearchService;
    private final CalculationRepairMeasurementService calculationRepairMeasurementService;
    private final MeasurementClient client;

    @Override
    public ResponseRepairMeasurementDto save(NewRepairMeasurementDto repairDto) {
        RepairMeasurement repair = mapper.mapToRepairMeasurement(repairDto);
        Set<RepairMeasurement> repairMeasurements = getAllByPredicate(repair);
        repair = duplicateSearchService.searchRepairDuplicate(repair, repairMeasurements);
        RepairLibrary repairLibrary = libraryService.getById(repair.getRepairLibraryId());
        if (repair.getId() == null) {
            EquipmentDto equipment = client.getEquipment(repairDto.getElementId(), repairDto.getPartElementId());
            mapper.mapWithRepairLibrary(repair, repairLibrary, equipment.getElementName(), equipment.getPartElementName());
            repair = repository.save(repair);
        }
        setMeasuredParameters(repair, repairLibrary, repairDto.getMeasuredParameters());
        calculationRepairMeasurementService.factory(repair, repairMeasurements, repairLibrary.getCalculation());
        return mapper.mapToResponseRepairMeasurementDto(repair);
    }

    @Override
    public ResponseRepairMeasurementDto update(UpdateRepairMeasurementDto repairDto) {
        RepairMeasurement repair = getById(repairDto.getId());
        Set<RepairMeasurement> repairMeasurements = getAllByPredicate(repair);
        RepairMeasurement duplicate = duplicateSearchService.searchRepairDuplicate(
                                                                          mapper.mapToUpdateRepairMeasurement(repairDto)
                                                                        , getAllByPredicate(repair));
        RepairLibrary repairLibrary = libraryService.getById(repair.getRepairLibraryId());
        if (!Objects.equals(repair.getId(), duplicate.getId())) {
            duplicate.setMeasuredParameters(
                    measuredParameterService.update(repair.getMeasuredParameters(), repairDto.getMeasuredParameters()));
            delete(repairDto.getId());
            repairMeasurements.remove(repair);
        } else {
            repair.setMeasuredParameters(
                    measuredParameterService.update(repair.getMeasuredParameters(), repairDto.getMeasuredParameters()));
            duplicate = repair;
        }
        calculationRepairMeasurementService.factory(repair, repairMeasurements, repairLibrary.getCalculation());
        return mapper.mapToResponseRepairMeasurementDto(duplicate);
    }

    private void setMeasuredParameters(RepairMeasurement repair
            , RepairLibrary repairLibrary
            , List<NewMeasuredParameterDto> measuredParameters) {
        repair.setMeasuredParameters(measuredParameterService.save(
                new ParameterMeasurementBuilder.Builder()
                        .libraryDataType(MeasurementType.REPAIR)
                        .repair(repair)
                        .measurementParameterLibraries(repairLibrary.getMeasuredParameters())
                        .newMeasuredParameters(measuredParameters)
                        .build()));
    }

    @Override
    public ResponseRepairMeasurementDto get(Long id) {
        return mapper.mapToResponseRepairMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseRepairMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<RepairMeasurement> repairMeasurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    repairMeasurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                            , elementId
                            , partElementId));
                }
                repairMeasurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            repairMeasurements.addAll(repository.findAllByEquipmentId(equipmentId));
        }
        return repairMeasurements.stream()
                                 .map(mapper::mapToResponseRepairMeasurementDto)
                                 .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Repair with id=%s not found for delete", id));
    }

    private RepairMeasurement getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Repair with id=%s not found", id)));
    }

    private Set<RepairMeasurement> getAllByPredicate(RepairMeasurement repair) {
        if (repair.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndRepairLibraryId(repair.getEquipmentId()
                                                                                        , repair.getElementId()
                                                                                        , repair.getPartElementId()
                                                                                        , repair.getRepairLibraryId());
        } else {
            return repository.findAllByEquipmentIdAndElementIdAndRepairLibraryId(repair.getEquipmentId()
                                                                               , repair.getElementId()
                                                                               , repair.getRepairLibraryId());
        }
    }
}