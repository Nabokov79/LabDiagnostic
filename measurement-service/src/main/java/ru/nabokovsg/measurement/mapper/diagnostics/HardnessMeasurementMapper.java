package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.ResponseElementHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.UpdateHardnessMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;

@Mapper(componentModel = "spring")
public interface HardnessMeasurementMapper {

    HardnessMeasurement mapToHardnessMeasurement(NewHardnessMeasurementDto measurementDto);

    HardnessMeasurement mapToUpdateHardnessMeasurement(UpdateHardnessMeasurementDto measurementDto);

    ResponseElementHardnessMeasurementDto mapToResponseHardnessMeasurementDto(HardnessMeasurement measurement);
}