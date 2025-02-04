package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.library.TypeMeasuredParameterBuilder;

import java.util.List;
import java.util.Set;

public interface MeasuredParameterLibraryService {

    Set<MeasurementParameterLibrary> save(TypeMeasuredParameterBuilder builder
                                        , List<NewMeasurementParameterLibraryDto> measuredParameters);

    Set<MeasurementParameterLibrary> update(TypeMeasuredParameterBuilder builder
                                          , List<UpdateMeasurementParameterLibraryDto> measuredParameters);
}