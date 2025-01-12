package ru.nabokovsg.measurement.service.qualityControl;

import ru.nabokovsg.measurement.dto.ultrasonicControl.NewUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.ResponseUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.UpdateUltrasonicControlDto;

import java.util.List;

public interface UltrasonicControlService {

    ResponseUltrasonicControlDto save(NewUltrasonicControlDto defectDto);

    ResponseUltrasonicControlDto update(UpdateUltrasonicControlDto defectDto);

   ResponseUltrasonicControlDto get(Long id);

    List<ResponseUltrasonicControlDto> getAll(Long equipmentId);

    void delete(Long id);
}