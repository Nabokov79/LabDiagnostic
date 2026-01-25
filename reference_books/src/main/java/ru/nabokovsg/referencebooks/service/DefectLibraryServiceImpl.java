package ru.nabokovsg.referencebooks.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
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
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.DefectValidator;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectLibraryServiceImpl implements DefectLibraryService {

    private final DefectLibraryRepository repository;
    private final DefectLibraryMapper mapper;
    private final EntityManager em;
    private final EquipmentLibraryService equipmentService;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final MeasuredParameterLibraryService measuredParameterService;
    private final DefectValidator validator;
    private final ToStringService toString;

    @Override
    public ResponseShortDefectLibraryDto save(NewDefectLibraryDto defectDto) {
        List<MeasurementParameterLibrary> measuredParameters =
                measuredParameterService.createNew(defectDto.getMeasuredParametersLibrary());
        DefectLibrary defect = build(mapper.mapToDefectLibrary(defectDto), measuredParameters);
        exists(defect);
        defect = repository.save(defect);
        measuredParameterService.saveDefectParameter(defect, measuredParameters);
        return mapper.mapToResponseShortDefectLibraryDto(defect);
    }

    @Override
    public ResponseShortDefectLibraryDto update(UpdateDefectLibraryDto defectDto) {
        DefectLibrary defect = getById(defectDto.getId());
        List<MeasurementParameterLibrary> measuredParameters =
                measuredParameterService.createUpdate(defectDto.getMeasuredParametersLibrary());
        mapper.mapToUpdateDefectLibrary(defect, defectDto);
        exists(defect);
        defect = repository.save(build(defect, measuredParameters));
        measuredParameterService.saveDefectParameter(defect, measuredParameters);
        return mapper.mapToResponseShortDefectLibraryDto(defect);
    }

    @Override
    public ResponseDefectLibraryDto get(Long id) {
        return mapper.mapToResponseDefectLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortDefectLibraryDto> getAll(String name, String documentation) {
        List<DefectLibrary> defects = repository.findAll();
        if (name != null) {
            final String defectName = name.toLowerCase();
            defects = defects.stream()
                    .filter(defect -> defect.getName().toLowerCase().contains(defectName))
                    .toList();
        }
        if (documentation != null) {
            final String documentationLibrary = documentation.toLowerCase();
            defects = defects.stream()
                    .filter(defect -> defect.getDocumentationLibrary() != null)
                    .filter(defect -> defect.getDocumentationLibrary().toLowerCase().contains(documentationLibrary))
                    .toList();
        }
        return defects.stream()
                .sorted(Comparator.comparing(DefectLibrary::getName))
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

    private DefectLibrary build(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParametersLibrary) {
        QualityAssessment qualityAssessmentType = getQualityAssessment(defect.getQualityAssessment());
        validator.validateDefectLibrary(qualityAssessmentType, defect, measuredParametersLibrary);
        mapper.mapWithFields(defect
                , equipmentService.getFullName(defect.getEquipmentLibraryId())
                , documentationService.getDocument(defect.getDocumentationLibraryId())
                , toString.measuredParameters(measuredParametersLibrary)
                , toString.thickness(defect.getMinThickness(), defect.getMaxThickness())
                , toString.additionalEvaluationParameters(defect.getTotalLengthMM(), defect.getTotalLengthPercentage())
                , toString.additionalEvaluationParameters(defect.getAssessmentAreaMM(), defect.getAssessmentAreaPercentage())
                , qualityAssessmentType.label
                , qualityAssessmentType);
        return defect;
    }

    private QualityAssessment getQualityAssessment(String qualityAssessmentType) {
        return QualityAssessment.from(qualityAssessmentType).orElseThrow(
                () -> new BadRequestException(String.format("Оценка качества не поддерживается: %s", qualityAssessmentType)));
    }

    private void exists(DefectLibrary defect) {
        boolean exists = false;
        if (defect.getId() == null) {
            exists = getDuplicate(defect) != null;
        } else {
            Long id = getDuplicate(defect);
            if (id != null) {
                exists = !defect.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label,
                    String.join(" ", defect.getName(), "по", defect.getDocumentationLibrary())));
        }
    }

    private Long getDuplicate(DefectLibrary defect) {
        QDefectLibrary defectLibrary = QDefectLibrary.defectLibrary;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(defectLibrary.equipmentLibraryId.eq(defect.getEquipmentLibraryId()));
        builder.and(defectLibrary.name.eq(defect.getName()));
        builder.and(defectLibrary.documentationLibraryId.eq(defect.getDocumentationLibraryId()));
        builder.and(defectLibrary.minThickness.eq(defect.getMinThickness()));
        builder.and(defectLibrary.maxThickness.eq(defect.getMaxThickness()));
        return new JPAQueryFactory(em).select(defectLibrary.id)
                .from(defectLibrary)
                .where(builder)
                .fetchOne();
    }
}