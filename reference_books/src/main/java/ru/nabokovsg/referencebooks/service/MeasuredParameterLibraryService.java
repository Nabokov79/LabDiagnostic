package ru.nabokovsg.referencebooks.service;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

import java.util.List;

@Validated
public interface MeasuredParameterLibraryService {

    List<MeasurementParameterLibrary> saveNewDefectParameter(DefectLibrary defect
                                                        , List<@Valid NewMeasurementParameterLibraryDto> measuredParameters);
    List<MeasurementParameterLibrary> saveNewRepairParameter(RepairLibrary repair
                                                        , List<@Valid NewMeasurementParameterLibraryDto> measuredParameters);

    List<MeasurementParameterLibrary> update(List<@Valid UpdateMeasurementParameterLibraryDto> measuredParametersDto);

    ResponseMeasurementParameterLibraryDto get(Long id);

    void delete(Long id);
}