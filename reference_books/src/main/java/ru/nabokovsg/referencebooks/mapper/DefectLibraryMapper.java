package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.QualityAssessment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    DefectLibrary mapToDefectLibrary(NewDefectLibraryDto defectDto);

    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    DefectLibrary mapToUpdateDefectLibrary(UpdateDefectLibraryDto defectDto);

    ResponseDefectLibraryDto mapToResponseDefectLibraryDto(DefectLibrary defect);

    ResponseDefectLibraryDto mapWithMeasurementParameter(DefectLibrary defect
                                                       , List<MeasurementParameterLibrary> measuredParameters);

    ResponseShortDefectLibraryDto mapToResponseShortDefectLibraryDto(DefectLibrary defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "withoutNamingParameter", ignore = true)
    @Mapping(target = "documentationLibraryId", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "assessmentAreaMM", ignore = true)
    @Mapping(target = "assessmentAreaPercentage", ignore = true)
    @Mapping(target = "defectsQuantity", ignore = true)
    @Mapping(target = "totalLengthMM", ignore = true)
    @Mapping(target = "totalLengthPercentage", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    void mapQualityAssessment(@MappingTarget DefectLibrary defect
                                           , QualityAssessment qualityAssessmentType
                                           , String qualityAssessment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "withoutNamingParameter", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "qualityAssessment", ignore = true)
    @Mapping(target = "documentationLibraryId", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "assessmentAreaMM", ignore = true)
    @Mapping(target = "assessmentAreaPercentage", ignore = true)
    @Mapping(target = "defectsQuantity", ignore = true)
    @Mapping(target = "totalLengthMM", ignore = true)
    @Mapping(target = "totalLengthPercentage", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    void mapDocumentationLibrary(@MappingTarget DefectLibrary defect, String documentationLibrary);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "withoutNamingParameter", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "qualityAssessment", ignore = true)
    @Mapping(target = "documentationLibraryId", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "assessmentAreaMM", ignore = true)
    @Mapping(target = "assessmentAreaPercentage", ignore = true)
    @Mapping(target = "defectsQuantity", ignore = true)
    @Mapping(target = "totalLengthMM", ignore = true)
    @Mapping(target = "totalLengthPercentage", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    void mapToStandardSize(@MappingTarget DefectLibrary defect, String standardSize);
}