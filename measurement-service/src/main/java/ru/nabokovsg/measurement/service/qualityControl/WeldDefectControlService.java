package ru.nabokovsg.measurement.service.qualityControl;

import ru.nabokovsg.measurement.dto.weldDefectControl.NewWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.ResponseWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.UpdateWeldDefectControlDto;

import java.util.List;

public interface WeldDefectControlService {

    ResponseWeldDefectControlDto save(NewWeldDefectControlDto defectDto);

    ResponseWeldDefectControlDto update(UpdateWeldDefectControlDto defectDto);

    ResponseWeldDefectControlDto get(Long id);

    List<ResponseWeldDefectControlDto> getAll(Long equipmentId);

    void delete(Long id);
}