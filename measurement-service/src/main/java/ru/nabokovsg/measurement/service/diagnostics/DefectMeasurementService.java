package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.UpdateDefectMeasurementDto;

import java.util.List;

public interface DefectMeasurementService {

    ResponseDefectMeasurementDto save(NewDefectMeasurementDto defectDto);

    ResponseDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto);

    ResponseDefectMeasurementDto get(Long id);

    List<ResponseDefectMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}