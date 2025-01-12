package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final MeasuredParameterService measuredParameterService;
    private final DuplicateSearchService duplicateSearchService;
    private final DefectLibraryService libraryService;
    private final CalculationDefectMeasurementService calculationDefectMeasurementService;

    @Override
    public ResponseDefectMeasurementDto save(NewDefectMeasurementDto defectDto) {
        DefectMeasurement defect = mapper.mapToDefectMeasurement(defectDto);
        Set<DefectMeasurement> defectMeasurements = getAllByPredicate(defect);
        defect = duplicateSearchService.searchDefectDuplicate(defect, defectMeasurements);
        DefectLibrary defectLibrary = libraryService.getById(defectDto.getDefectLibraryId());
        if (defect.getId() == null) {
            mapper.mapWitDefectLibrary(defect, defectLibrary);
            defect = repository.save(defect);
        }
        setMeasuredParameters(defect, defectLibrary, defectDto.getMeasuredParameters());
        calculationDefectMeasurementService.factory(defect, defectMeasurements, defectLibrary.getCalculation());
        return mapper.mapToResponseDefectMeasurementDto(defect);
    }

    @Override
    public ResponseDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto) {
        DefectMeasurement defect = getById(defectDto.getId());
        Set<DefectMeasurement> defectMeasurements = getAllByPredicate(defect);
        DefectMeasurement duplicate = duplicateSearchService.searchDefectDuplicate(
                                                      mapper.mapToUpdateDefectMeasurement(defectDto)
                                                    , defectMeasurements);
        DefectLibrary defectLibrary = libraryService.getById(defect.getDefectLibraryId());
        if (!Objects.equals(defect.getId(), duplicate.getId())) {
            duplicate.setMeasuredParameters(
                    measuredParameterService.update(defect.getMeasuredParameters(), defectDto.getMeasuredParameters()));
            delete(defectDto.getId());
            defectMeasurements.remove(defect);
        } else {
            defect.setMeasuredParameters(
                    measuredParameterService.update(defect.getMeasuredParameters(), defectDto.getMeasuredParameters()));
            duplicate = defect;
        }
        calculationDefectMeasurementService.factory(defect, defectMeasurements, defectLibrary.getCalculation());
        return mapper.mapToResponseDefectMeasurementDto(repository.save(duplicate));
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