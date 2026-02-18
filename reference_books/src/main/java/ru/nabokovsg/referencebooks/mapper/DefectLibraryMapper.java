package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model_enum.QualityAssessment;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

@Mapper(componentModel = "spring")
public interface DefectLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "totalLength", ignore = true)
    @Mapping(target = "assessmentArea", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    DefectLibrary mapToDefectLibrary(NewDefectLibraryDto defectDto);

    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "totalLength", ignore = true)
    @Mapping(target = "assessmentArea", ignore = true)
    @Mapping(target = "qualityAssessmentType", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    void mapToUpdateDefectLibrary(@MappingTarget DefectLibrary defect, UpdateDefectLibraryDto defectDto);

    @Mapping(source = "defect.equipment.id", target = "equipmentId")
    @Mapping(source = "defect.documentation.id", target = "documentationId")
    ResponseDefectLibraryDto mapToResponseDefectLibraryDto(DefectLibrary defect);

    @Mapping(source = "defect.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "defect.documentation.document", target = "documentation")
    ResponseShortDefectLibraryDto mapToResponseShortDefectLibraryDto(DefectLibrary defect);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "defectsQuantity", ignore = true)
    @Mapping(target = "withoutNamingParameter", ignore = true)
    @Mapping(target = "assessmentAreaMM", ignore = true)
    @Mapping(target = "assessmentAreaPercentage", ignore = true)
    @Mapping(target = "totalLengthMM", ignore = true)
    @Mapping(target = "totalLengthPercentage", ignore = true)
    @Mapping(target = "minDiameter", ignore = true)
    @Mapping(target = "maxDiameter", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    void mapWithFields(@MappingTarget DefectLibrary defect
                                           , EquipmentLibrary equipment
                                           , RegulatoryDocumentationLibrary documentation
                                           , String measuredParameters
                                           , String thickness
                                           , String totalLength
                                           , String assessmentArea
                                           , String qualityAssessment
                                           , QualityAssessment qualityAssessmentType);
}