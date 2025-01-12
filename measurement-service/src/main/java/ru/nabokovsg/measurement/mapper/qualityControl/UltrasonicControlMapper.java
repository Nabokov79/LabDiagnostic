package ru.nabokovsg.measurement.mapper.qualityControl;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.ultrasonicControl.NewUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.ResponseUltrasonicControlDto;
import ru.nabokovsg.measurement.dto.ultrasonicControl.UpdateUltrasonicControlDto;
import ru.nabokovsg.measurement.model.qualityControl.UltrasonicControl;

@Mapper(componentModel = "spring")
public interface UltrasonicControlMapper {

    UltrasonicControl mapToUltrasonicControl(NewUltrasonicControlDto defectDto);

    UltrasonicControl mapToUpdateUltrasonicControl(UpdateUltrasonicControlDto defectDto);

    ResponseUltrasonicControlDto mapToResponseUltrasonicControlDto(UltrasonicControl defect);

    void mapToPositiveQualityAssessment(@MappingTarget UltrasonicControl defect
            , String descriptionDefect
            , String coordinates);
}