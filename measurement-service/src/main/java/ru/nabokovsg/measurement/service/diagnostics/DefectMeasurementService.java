package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.defectMeasurement.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseShortDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.UpdateDefectMeasurementDto;

import java.util.List;

public interface DefectMeasurementService {

    ResponseShortDefectMeasurementDto save(NewDefectMeasurementDto defectDto);

    ResponseShortDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto);

    ResponseDefectMeasurementDto get(Long id);

    List<ResponseShortDefectMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}