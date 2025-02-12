package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.NewAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.ResponseAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.UpdateAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;

@Mapper(componentModel = "spring")
public interface AcceptableMetalHardnessMapper {

    AcceptableMetalHardness mapToAcceptableHardness(NewAcceptableMetalHardnessDto hardnessDto
                                                  , String standardSize);

    AcceptableMetalHardness mapToUpdateAcceptableHardness(UpdateAcceptableMetalHardnessDto hardnessDto
                                                        , String standardSize);

    ResponseAcceptableMetalHardnessDto mapToResponseAcceptableMetalHardnessDto(AcceptableMetalHardness hardness);
}