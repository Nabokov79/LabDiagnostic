package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.UpdateDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.DefectMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.DefectMeasurementRepository;
import ru.nabokovsg.measurement.service.library.DefectLibraryService;
import ru.nabokovsg.measurement.сalculation.CalculationDefectMeasurementService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final MeasuredParameterService measuredParameterService;
    private final DuplicateSearchService duplicateSearchService;
    private final DefectLibraryService libraryService;
    private final CalculationDefectMeasurementService calculationDefectMeasurementService;
    private final MeasurementClient client;

    @Override
    public ResponseDefectMeasurementDto save(NewDefectMeasurementDto defectDto) {
        DefectMeasurement defect = mapper.mapToDefectMeasurement(defectDto);
        Set<DefectMeasurement> defectMeasurements = getAllByPredicate(defect);
        defect = getDuplicate(defect, defectMeasurements);
        DefectLibrary defectLibrary = libraryService.getById(defectDto.getDefectLibraryId());
        if (defect.getId() == null) {
            EquipmentDto equipment = client.getEquipment(defectDto.getElementId(), defectDto.getPartElementId());
            mapper.mapWitDefectLibrary(defect, defectLibrary, equipment.getElementName(), equipment.getPartElementName());
            setMeasuredParameters(defect, defectLibrary, defectDto.getMeasuredParameters());
            defect = repository.save(defect);
        }
        log.info("Save DefectMeasurement:");
        if (defectMeasurements.isEmpty()) {
            defectMeasurements.add(defect);
        }
        calculationDefectMeasurementService.factory(defect, defectMeasurements, defectLibrary.getCalculation());
        return mapper.mapToResponseDefectMeasurementDto(defect);
    }

    private DefectMeasurement getDuplicate(DefectMeasurement defect, Set<DefectMeasurement> defectMeasurements) {
        return Objects.requireNonNullElse(duplicateSearchService.searchDefectDuplicate(defect, defectMeasurements), defect);
    }

    @Override
    public ResponseDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto) {
        DefectMeasurement defect = getById(defectDto.getId());
        Set<DefectMeasurement> defectMeasurements = getAllByPredicate(defect);
        defect = getDuplicate(defect, defectMeasurements);
        DefectLibrary defectLibrary = libraryService.getById(defect.getDefectLibraryId());
        if (!Objects.equals(defectDto.getId(), defect.getId())) {
            delete(defectDto.getId());
            defectMeasurements.remove(defect);
        }
        defect.setMeasuredParameters(
                measuredParameterService.update(defect.getMeasuredParameters(), defectDto.getMeasuredParameters()));
        calculationDefectMeasurementService.factory(defect, defectMeasurements, defectLibrary.getCalculation());
        return mapper.mapToResponseDefectMeasurementDto(defect);
    }

    @Override
    public ResponseDefectMeasurementDto get(Long id) {
        return mapper.mapToResponseDefectMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseDefectMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<DefectMeasurement> defectMeasurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    defectMeasurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                                                                                                   , elementId
                                                                                                   , partElementId));
                }
                defectMeasurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            defectMeasurements.addAll(repository.findAllByEquipmentId(equipmentId));
        }
        return defectMeasurements.stream()
                                 .map(mapper::mapToResponseDefectMeasurementDto)
                                 .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Defect measurement with id=%s not found for delete", id));
    }

    private DefectMeasurement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Defect measurement with id=%s not found", id)));
    }

    private Set<DefectMeasurement> getAllByPredicate(DefectMeasurement identifiedDefect) {
        if (identifiedDefect.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectLibraryId(
                                                                              identifiedDefect.getEquipmentId()
                                                                            , identifiedDefect.getElementId()
                                                                            , identifiedDefect.getPartElementId()
                                                                            , identifiedDefect.getDefectLibraryId());
        } else {
            return repository.findAllByEquipmentIdAndElementIdAndDefectLibraryId(identifiedDefect.getEquipmentId()
                                                                               , identifiedDefect.getElementId()
                                                                               , identifiedDefect.getDefectLibraryId());
        }
    }

    private void setMeasuredParameters(DefectMeasurement defect
                                     , DefectLibrary defectLibrary
                                     , List<NewMeasuredParameterDto> measuredParameters) {
        defect.setMeasuredParameters(measuredParameterService.save(
                new ParameterMeasurementBuilder.Builder()
                        .libraryDataType(MeasurementType.DEFECT)
                        .defect(defect)
                        .measurementParameterLibraries(defectLibrary.getMeasuredParameters())
                        .newMeasuredParameters(measuredParameters)
                        .build()));
    }
}