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
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DefectLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.QualityAssessment;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;
import ru.nabokovsg.referencebooks.repository.DefectLibraryRepository;
import ru.nabokovsg.referencebooks.model.QDefectLibrary;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectLibraryServiceImpl implements DefectLibraryService {

    private final DefectLibraryRepository repository;
    private final DefectLibraryMapper mapper;
    private final EntityManager em;
    private final RegulatoryDocumentationLibraryService documentationLibraryService;
    private final MeasuredParameterLibraryService measuredParameterService;

    @Override
    public ResponseDefectLibraryDto save(NewDefectLibraryDto defectDto) {
        validateByDuplicate(defectDto.getEquipmentLibraryId(), defectDto.getName()
                          , defectDto.getDocumentationLibraryId()
                          , defectDto.getMinThickness(), defectDto.getMaxThickness());
        DefectLibrary defect = repository.save(build(mapper.mapToDefectLibrary(defectDto)));
        if (defectDto.getMeasuredParameters() != null) {
            return mapper.mapWithMeasurementParameter(
                    defect
                    , measuredParameterService.saveNewDefectParameter(defect, defectDto.getMeasuredParameters()));
        }
        return mapper.mapToResponseDefectLibraryDto(defect);
    }

    @Override
    public ResponseDefectLibraryDto update(UpdateDefectLibraryDto defectDto) {
        validateByDuplicate(defectDto.getEquipmentLibraryId(), defectDto.getName()
                , defectDto.getDocumentationLibraryId()
                , defectDto.getMinThickness(), defectDto.getMaxThickness());
        if (repository.existsById(defectDto.getId())) {
            if (defectDto.getMeasuredParameters() != null) {
                return mapper.mapWithMeasurementParameter(
                        repository.save(build(mapper.mapToUpdateDefectLibrary(defectDto)))
                        , measuredParameterService.update(defectDto.getMeasuredParameters()));
            }
            return mapper.mapToResponseDefectLibraryDto(
                    repository.save(build(mapper.mapToUpdateDefectLibrary(defectDto))));
        }
        throw new NotFoundException(ExceptionMassage.NOT_DEFECT.label);
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

    private DefectLibrary build(DefectLibrary defect) {
        QualityAssessment qualityAssessmentType = getQualityAssessment(defect.getQualityAssessment());
        RegulatoryDocumentationLibrary documentation = documentationLibraryService.getById(defect.getDocumentationLibraryId());
        mapper.mapQualityAssessment(defect, qualityAssessmentType, qualityAssessmentType.label);
        mapper.mapDocumentationLibrary(defect, String.join(" ", documentation.getView(), documentation.getNumber()));
        mapper.mapToStandardSize(defect, getStandardsSize(defect.getMaxThickness(), defect.getMinThickness()));
        return defect;
    }
    private QualityAssessment getQualityAssessment(String qualityAssessmentType) {
        return QualityAssessment.from(qualityAssessmentType).orElseThrow(
                () -> new BadRequestException(String.format("Оценка качества не поддерживается: %s", qualityAssessmentType)));
    }

    private String getStandardsSize(Float maxThickness, Float minThickness) {
        String standardSize = null;
        if (minThickness != null) {
            standardSize = String.join(" ", "от", String.valueOf(minThickness));
        }
        if (maxThickness != null) {
            if (standardSize == null) {
                standardSize = String.join(" ", "до", String.valueOf(maxThickness), "включительно");
            } else {
                standardSize = String.join(" ", standardSize, "до", String.valueOf(maxThickness), "включительно");
            }
        }
        return standardSize;
    }

    private void validateByDuplicate(Long equipmentLibraryId, String name
                                   , Long documentationLibraryId, Float minThickness, Float maxThickness) {
        QDefectLibrary defectLibrary = QDefectLibrary.defectLibrary;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(defectLibrary.equipmentLibraryId.eq(equipmentLibraryId));
        builder.and(defectLibrary.name.eq(name));
        builder.and(defectLibrary.documentationLibraryId.eq(documentationLibraryId));
        if (minThickness != null) {
            builder.and(defectLibrary.minThickness.eq(minThickness));
        }
        if (maxThickness != null) {
            builder.and(defectLibrary.maxThickness.eq(maxThickness));
        }
        boolean exists = new JPAQueryFactory(em).select(defectLibrary)
                                                .from(defectLibrary)
                                                .where(builder)
                                                .fetchOne() != null;
        if (exists) {
            throw new BadRequestException(String.join("",ExceptionMassage.DUPLICATE.label, name));
        }
    }
}