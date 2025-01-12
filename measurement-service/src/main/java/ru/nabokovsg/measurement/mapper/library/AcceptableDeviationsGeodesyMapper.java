package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.NewAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy.UpdateAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;

@Mapper(componentModel = "spring")
public interface AcceptableDeviationsGeodesyMapper {

    AcceptableDeviationsGeodesy mapToAcceptableDeviationsGeodesy(NewAcceptableDeviationsGeodesyDto geodesyDto);

    AcceptableDeviationsGeodesy mapToUpdateAcceptableDeviationsGeodesy(UpdateAcceptableDeviationsGeodesyDto geodesyDto);

    ResponseAcceptableDeviationsGeodesyDto mapToResponseAcceptableDeviationsGeodesyDto(
                                                                         AcceptableDeviationsGeodesy geodesy);
}