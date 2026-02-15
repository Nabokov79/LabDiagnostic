package ru.nabokovsg.referencebooks.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.QDefectLibrary;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectDuplicateSearchServiceImpl implements DefectDuplicateSearchService {

    private final EntityManager em;

    @Override
    public void exists(DefectLibrary defect, Long equipmentId, Long documentationId) {
        boolean exists = false;
        if (defect.getId() == null) {
            exists = toFindDuplicate(defect, equipmentId, documentationId);
        } else {
            Long id = toFindAllDuplicate(defect, equipmentId, documentationId);
            if (id != null) {
                exists = !defect.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label,
                    String.join(" ", defect.getName(), "по", defect.getDocumentation().getDocument())));
        }
    }

    private boolean toFindDuplicate(DefectLibrary defect, Long equipmentId, Long documentationId) {
        QDefectLibrary defectLibrary = QDefectLibrary.defectLibrary;
        return new JPAQueryFactory(em).select(defectLibrary.id)
                .from(defectLibrary)
                .where(getPredicate(defectLibrary, defect, equipmentId, documentationId))
                .fetchOne()!= null;
    }

    private Long toFindAllDuplicate(DefectLibrary defect, Long equipmentId, Long documentationId) {
        QDefectLibrary defectLibrary = QDefectLibrary.defectLibrary;
        List<DefectLibrary> defects =  new JPAQueryFactory(em).select(defectLibrary)
                .from(defectLibrary)
                .where(getPredicate(defectLibrary, defect, equipmentId, documentationId))
                .fetch();
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

    private BooleanBuilder getPredicate(QDefectLibrary defectLibrary, DefectLibrary defect, Long equipmentId, Long documentationId) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(defectLibrary.equipment.id.eq(equipmentId));
        builder.and(defectLibrary.name.eq(defect.getName()));
        builder.and(defectLibrary.documentation.id.eq(documentationId));
        if (defect.getMinThickness() != null || defect.getMaxThickness() != null) {
            if (defect.getMinThickness() != null) {
                builder.and(defectLibrary.minThickness.eq(defect.getMinThickness()));
            }
            if (defect.getMaxThickness() != null) {
                builder.and(defectLibrary.maxThickness.eq(defect.getMaxThickness()));
            }
        }
        return builder;
    }
}