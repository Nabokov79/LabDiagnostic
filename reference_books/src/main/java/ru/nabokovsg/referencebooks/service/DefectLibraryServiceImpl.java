package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DefectLibraryMapper;
import ru.nabokovsg.referencebooks.repository.DefectLibraryRepository;
import ru.nabokovsg.referencebooks.search.DefectDuplicateSearchService;
import ru.nabokovsg.referencebooks.search.SearchService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.DefectValidator;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefectLibraryServiceImpl implements DefectLibraryService {

    private final DefectLibraryRepository repository;
    private final DefectLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final MeasuredParameterLibraryService measuredParameterService;
    private final DefectValidator validator;
    private final ToStringService toString;
    private final SearchService searchService;
    private final DefectDuplicateSearchService duplicateSearchService;

    @Override
    public ResponseShortDefectLibraryDto save(NewDefectLibraryDto defectDto) {
        List<MeasurementParameterLibrary> measuredParameters =
                measuredParameterService.create(defectDto.getMeasuredParametersLibrary());
        DefectLibrary defect = mapper.mapToDefectLibrary(defectDto);
        build(defect, measuredParameters, defectDto.getEquipmentId(), defectDto.getDocumentationId());
        defect = repository.save(defect);
        measuredParameterService.saveDefectParameter(defect, measuredParameters);
        return mapper.mapToResponseShortDefectLibraryDto(defect);
    }

    @Override
    public ResponseShortDefectLibraryDto update(UpdateDefectLibraryDto defectDto) {
        DefectLibrary defect = getById(defectDto.getId());
        mapper.mapToUpdateDefectLibrary(defect, defectDto);
        measuredParameterService.update(defect.getMeasuredParametersLibrary(), defectDto.getMeasuredParametersLibrary());
        build(defect, defect.getMeasuredParametersLibrary(), defectDto.getEquipmentId(), defectDto.getDocumentationId());
        measuredParameterService.saveDefectParameter(defect, defect.getMeasuredParametersLibrary());
        return mapper.mapToResponseShortDefectLibraryDto(repository.save(defect));
    }

    @Override
    public ResponseDefectLibraryDto get(Long id) {
        return mapper.mapToResponseDefectLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortDefectLibraryDto> getAll(String search) {
        Set<DefectLibrary> defects = repository.findAllOrderByName();
        if (search != null) {
            return defects.stream()
                    .filter(d ->
                            searchService.search(search, List.of(d.getEquipment().getEquipmentFullName()
                                                               , d.getDocumentation().getDocument()
                                                               , d.getName())))
                          .map(mapper::mapToResponseShortDefectLibraryDto)
                          .toList();
        }
        return defects.stream()
                .map(mapper::mapToResponseShortDefectLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(ExceptionMassage.NOT_DEFECT.label);
    }

    @Override
    public DefectLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMassage.NOT_DEFECT.label));
    }

    private void build(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParameters, Long equipmentId, Long documentationId) {
        QualityAssessment qualityAssessmentType = getQualityAssessment(defect.getQualityAssessment());
        mapper.mapWithFields(defect
                , equipmentService.getById(equipmentId)
                , documentationService.getById(documentationId)
                , toString.measuredParameters(measuredParameters)
                , toString.thickness(defect.getMinThickness(), defect.getMaxThickness())
                , toString.additionalEvaluationParameters(defect.getTotalLengthMM(), defect.getTotalLengthPercentage())
                , toString.additionalEvaluationParameters(defect.getAssessmentAreaMM(), defect.getAssessmentAreaPercentage())
                , qualityAssessmentType.label
                , qualityAssessmentType);
        validator.validate(defect, measuredParameters);
        duplicateSearchService.exists(defect, equipmentId, documentationId);
    }

    private QualityAssessment getQualityAssessment(String qualityAssessmentType) {
        return QualityAssessment.from(qualityAssessmentType).orElseThrow(
                () -> new BadRequestException(String.format("Оценка качества не поддерживается: %s", qualityAssessmentType)));
    }
}