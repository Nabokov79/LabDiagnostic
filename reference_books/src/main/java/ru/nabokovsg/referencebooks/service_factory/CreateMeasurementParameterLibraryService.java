package ru.nabokovsg.referencebooks.service_factory;

import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

public interface CreateMeasurementParameterLibraryService {

    void replaceEquals(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters);

    void replaceLess(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters);
    void replaceMore(List<MeasurementParameterLibrary> measuredParametersLibrary, List<MeasurementParameterLibraryDto> measuredParameters, List<Long> delete);

    MeasurementParameterLibrary create(MeasurementParameterLibraryDto parameter);
}