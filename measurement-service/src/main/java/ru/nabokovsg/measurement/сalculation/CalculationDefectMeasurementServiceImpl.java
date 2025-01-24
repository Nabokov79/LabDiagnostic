package ru.nabokovsg.measurement.сalculation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationDefectMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.*;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationDefectMeasurementRepository;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationDefectMeasurementServiceImpl implements CalculationDefectMeasurementService {

    private final CalculationDefectMeasurementRepository repository;
    private final CalculationDefectMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;
    private final EntityManager em;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public void save(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type) {
        defects.add(defect);
        calculation(defect, defects, type);
    }

    @Override
    public void delete(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type) {
        if (defects.isEmpty()) {
            deleteAll(defect);
        } else {
            calculation(defect, defects, type);
        }
    }

    private void calculation(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type) {
        switch (type) {
            case MIN, MAX, MAX_MIN -> saveOne(defect, defects, type);
            case NO_ACTION -> saveAll(defect, defects);
        }
    }

    private void saveOne(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type) {
        Set<MeasuredParameter> parameters = defects.stream()
                .map(DefectMeasurement::getMeasuredParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        CalculationDefectMeasurement calculationDefect = get(defect);
        String parametersString = calculateDefectParameters(parameters, type);
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, parametersString);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationDefect, defect.getUnacceptable(), parametersString);
        }
        repository.save(calculationDefect);
    }

    private void saveAll(DefectMeasurement defectMeasurement, Set<DefectMeasurement> defects) {
        Set<CalculationDefectMeasurement> calculationDefectsDb = getAll(defectMeasurement);
        List<CalculationDefectMeasurement> calculationDefects = defects
                .stream()
                .map(defect -> mapper.mapToCalculationDefectMeasurement(defect,
                        stringBuilder.convertMeasuredParameter(defect.getMeasuredParameters())))
                .toList();
        if (!calculationDefectsDb.isEmpty()) {
            updateAll(calculationDefectsDb, calculationDefects);
            repository.saveAll(calculationDefectsDb);
            return;
        }
        repository.saveAll(calculationDefects);
    }

    private String calculateDefectParameters(Set<MeasuredParameter> parameters
                                                                              , ParameterCalculationType type) {
        Map<String, CalculationMeasuredParameter> calculationParameters = new HashMap<>();
        switch (type) {
            case MIN -> calculationParameterService.countMin(parameters, calculationParameters);
            case MAX -> calculationParameterService.countMax(parameters, calculationParameters);
            case MAX_MIN -> calculationParameterService.countMinMax(parameters, calculationParameters);
            default -> throw new BadRequestException(
                    String.format("The type of defect calculation is not supported: %s", type));
        }
        return stringBuilder.convertCalculationParameters(calculationParameters);
    }

    private void updateAll(Set<CalculationDefectMeasurement> defectsDb, List<CalculationDefectMeasurement> defects) {
        int index = 0;
        for (CalculationDefectMeasurement defect : defectsDb) {
            mapper.mapToUpdateCalculationDefectMeasurement(defects.get(index), defect);
            index++;
        }
    }

    private Set<CalculationDefectMeasurement> getAll(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectName(defect.getEquipmentId()
                                                                                          , defect.getElementId()
                                                                                          , defect.getPartElementId()
                                                                                          , defect.getDefectName());
        }
        return repository.findAllByEquipmentIdAndElementIdAndDefectName(defect.getEquipmentId()
                                                                      , defect.getElementId()
                                                                      , defect.getDefectName());
    }

    private CalculationDefectMeasurement get(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            return repository.findByEquipmentIdAndElementIdAndPartElementIdAndDefectName(defect.getEquipmentId()
                    , defect.getElementId()
                    , defect.getPartElementId()
                    , defect.getDefectName());
        }
        return repository.findByEquipmentIdAndElementIdAndDefectName(defect.getEquipmentId()
                , defect.getElementId()
                , defect.getDefectName());
    }

    private void deleteAll(DefectMeasurement defect) {
        QCalculationDefectMeasurement defectMeasurement = QCalculationDefectMeasurement.calculationDefectMeasurement;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(defectMeasurement.equipmentId.eq(defect.getEquipmentId()));
        builder.and(defectMeasurement.elementId.eq(defect.getElementId()));
        builder.and(defectMeasurement.defectName.eq(defect.getDefectName()));
        if (defect.getPartElementId() != null) {
            builder.and(defectMeasurement.partElementId.eq(defect.getPartElementId()));
        }
        new JPAQueryFactory(em)
                .delete(defectMeasurement)
                .where(builder);
    }
}