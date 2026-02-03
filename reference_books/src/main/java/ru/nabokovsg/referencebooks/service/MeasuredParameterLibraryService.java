package ru.nabokovsg.referencebooks.service;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

import java.util.List;

@Validated
public interface MeasuredParameterLibraryService {

    void saveDefectParameter(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParameters);

    void saveRepairParameter(RepairLibrary repair, List<MeasurementParameterLibrary> measuredParameters);

    List<MeasurementParameterLibrary> create(List<@Valid MeasurementParameterLibraryDto> measuredParametersDto);

    void update(List<MeasurementParameterLibrary> measuredParametersLibrary
              , List<@Valid MeasurementParameterLibraryDto> measuredParametersDto);
}