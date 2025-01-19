package ru.nabokovsg.measurement.сalculation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationDefectMeasurementMapper;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.QCalculationDefectMeasurement;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.repository.diagnostics.CalculationDefectMeasurementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationDefectMeasurementServiceImpl implements CalculationDefectMeasurementService {

    private final CalculationDefectMeasurementRepository repository;
    private final CalculationDefectMeasurementMapper mapper;
    private final CalculationMeasuredParameterService calculationParameterService;
    private final EntityManager em;

    @Override
    public void factory(DefectMeasurement defect
                      , Set<DefectMeasurement> defectMeasurements
                      , ParameterCalculationType calculation) {
        if (defectMeasurements.isEmpty()) {
            deleteAll(defect);
            return;
        }
        switch (calculation) {
            case MIN, MAX, MAX_MIN -> {
                Set<MeasuredParameter> measuredParameters = defectMeasurements.stream()
                                                                        .map(DefectMeasurement::getMeasuredParameters)
                                                                        .flatMap(Collection::stream)
                                                                        .collect(Collectors.toSet());
                saveOne(defect, measuredParameters, calculation);
            }
            case NO_ACTION -> saveAll(defect, defectMeasurements, calculation);
        }
    }

    private void saveOne(DefectMeasurement defect
                       , Set<MeasuredParameter> measuredParameters
                       , ParameterCalculationType calculation) {
        CalculationDefectMeasurement calculationDefect = get(defect);
        String parameters = calculationParameterService.getMeasuredParameters(measuredParameters, calculation);
        if (calculationDefect == null) {
            calculationDefect = mapper.mapToCalculationDefectMeasurement(defect, parameters);
        } else {
            mapper.mapToUpdateMeasuredParameters(calculationDefect, defect.getUnacceptable(), parameters);
        }
        repository.save(calculationDefect);
    }

    private void saveAll(DefectMeasurement defect
                       , Set<DefectMeasurement> defectMeasurements
                       , ParameterCalculationType calculation) {
        Set<CalculationDefectMeasurement> defectsDb = getAll(defect);
        List<CalculationDefectMeasurement> defects = defectMeasurements
                .stream()
                .map(defectMeasurement ->  mapper.mapToCalculationDefectMeasurement(defect
                                                       , calculationParameterService.getMeasuredParameters(
                                                               defectMeasurement.getMeasuredParameters(), calculation)))
                .toList();
        if (!defectsDb.isEmpty()) {
            updateAll(defectsDb, defects);
            repository.saveAll(defectsDb);
            return;
        }
        repository.saveAll(defects);
    }

    private void updateAll(Set<CalculationDefectMeasurement> defectsDb, List<CalculationDefectMeasurement> defects) {
        int index = 0;
        for (CalculationDefectMeasurement defect : defectsDb) {
            mapper.mapToUpdateCalculationDefectMeasurement(defects.get(index), defect);
            index++;
        }
        repository.saveAll(defectsDb);
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