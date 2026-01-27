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

import java.util.List;
import java.util.Set;

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
    public List<ResponseShortDefectLibraryDto> getAll(String defect) {
        Set<DefectLibrary> defects = repository.findAllOrderByName();
        if (defect != null) {
            final String defectName = defect.toLowerCase();
            return defects.stream()
                          .filter(d -> d.getEquipmentLibrary().toLowerCase().contains(defectName)
                                    || d.getDocumentationLibrary().toLowerCase().contains(defectName)
                                    || d.getName().toLowerCase().contains(defectName))
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

    private DefectLibrary build(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParametersLibrary) {
        QualityAssessment qualityAssessmentType = getQualityAssessment(defect.getQualityAssessment());
        validator.validate(defect);
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
        if (defect.getMinThickness() != null || defect.getMaxThickness() != null) {
            if (defect.getMinThickness() != null) {
                builder.and(defectLibrary.minThickness.eq(defect.getMinThickness()));
            }
            if (defect.getMaxThickness() != null) {
                builder.and(defectLibrary.maxThickness.eq(defect.getMaxThickness()));
            }
            return new JPAQueryFactory(em).select(defectLibrary.id)
                                          .from(defectLibrary)
                                          .where(builder)
                                          .fetchOne();
        }
        return filterDuplicates(defect, new JPAQueryFactory(em).select(defectLibrary)
                                                               .from(defectLibrary)
                                                               .where(builder)
                                                               .fetch());
    }

    private Long filterDuplicates(DefectLibrary defect, List<DefectLibrary> defects) {
        Long[] id = {null};
        if (defect.getMinThickness() != null && defect.getMaxThickness() != null) {
            defects.forEach(v -> {
                if (v.getMinThickness() != null && v.getMaxThickness() != null) {
                    id[0] = v.getId();
                }
            });
        }
        if (defect.getMinThickness() == null) {
            defects.forEach(v -> {
                if (v.getMinThickness() == null) {
                    id[0] = v.getId();
                }
            });
        }
        if (defect.getMaxThickness() == null) {
            defects.forEach(v -> {
                if (v.getMaxThickness() == null) {
                    id[0] = v.getId();
                }
            });
        }
        return id[0];
    }
}