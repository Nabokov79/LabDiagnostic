package ru.nabokovsg.referencebooks.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseShortResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.ResidualThicknessLibraryMapper;
import ru.nabokovsg.referencebooks.model.QResidualThicknessLibrary;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.ResidualThicknessLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.EquipmentInformationBuilderService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.SizeAcceptableCheckingValidator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidualThicknessLibraryServiceImpl implements ResidualThicknessLibraryService {

    private final ResidualThicknessLibraryRepository repository;
    private final ResidualThicknessLibraryMapper mapper;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final SizeAcceptableCheckingValidator validator;
    private final EquipmentInformationBuilderService builderService;
    private final EntityManager em;
    private final ToStringService toString;
    private final static String MASSAGE = "Допустимые значения остаточной толщины не обнаружены.";

    @Override
    public ResponseShortResidualThicknessLibraryDto save(NewResidualThicknessLibraryDto thicknessDto) {
        ResidualThicknessLibrary residualThickness = mapper.mapToAcceptableThickness(thicknessDto);
        validate(residualThickness);
        build(residualThickness);
        return mapper.mapToResponseShortResidualThicknessLibraryDto(repository.save(residualThickness));
    }

    @Override
    public ResponseShortResidualThicknessLibraryDto update(UpdateResidualThicknessLibraryDto thicknessDto) {
        ResidualThicknessLibrary residualThickness = getById(thicknessDto.getId());
        mapper.mapToUpdateAcceptableThickness(residualThickness, thicknessDto);
        validate(residualThickness);
        build(residualThickness);
        return mapper.mapToResponseShortResidualThicknessLibraryDto(repository.save(residualThickness));
    }

    @Override
    public ResponseResidualThicknessLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableResidualThicknessDto(getById(id));
    }

    @Override
    public List<ResponseShortResidualThicknessLibraryDto> getAll(String name) {
        Set<ResidualThicknessLibrary> thicknesses = repository.findAllOrderByElementNameDesc();
        if (name != null) {
            final String search = name.toLowerCase();
            thicknesses = thicknesses.stream()
                    .filter(hardness ->
                            hardness.getEquipmentFullName().toLowerCase().contains(search)
                                    || hardness.getElementFullName().toLowerCase().contains(search)
                                    || hardness.getDocumentationLibrary().toLowerCase().contains(search))
                    .collect(Collectors.toSet());
        }
        return thicknesses.stream()
                .map(mapper::mapToResponseShortResidualThicknessLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }

    private ResidualThicknessLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void build(ResidualThicknessLibrary residualThickness) {
        Map<String, String> information;
        if (residualThickness.getPartElementLibraryId() != null) {
            information = builderService.getByPartElement(residualThickness.getPartElementLibraryId());
        } else {
            information = builderService.getByElement(residualThickness.getElementLibraryId());
        }
        String documentationLibrary = documentationService.getDocument(residualThickness.getDocumentationLibraryId());
        information.forEach((k,v) ->
                mapper.mapToResidualThicknessLibrary(residualThickness, k, v, documentationLibrary
                                                        , toString.getStandardSize(residualThickness.getDiameter()
                                                                                , residualThickness.getThickness())));
        exists(residualThickness);
    }

    private void validate(ResidualThicknessLibrary residualThickness) {
        validator.validateStandardSize(residualThickness.getDiameter(), residualThickness.getThickness());
        validator.validateResidualThickness(residualThickness.getMinAcceptableThicknessMM()
                , residualThickness.getMinAcceptableThicknessPercent()
                , residualThickness.getMaxAcceptableThinningMM()
                , residualThickness.getMaxAcceptableThinningPercent());
    }

    private void exists(ResidualThicknessLibrary thickness) {
        QResidualThicknessLibrary residualThickness = QResidualThicknessLibrary.residualThicknessLibrary;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(residualThickness.documentationLibraryId.eq(thickness.getDocumentationLibraryId()));
        builder.and(residualThickness.equipmentLibraryId.eq(thickness.getEquipmentLibraryId()));
        builder.and(residualThickness.elementLibraryId.eq(thickness.getElementLibraryId()));
        if (thickness.getPartElementLibraryId() != null) {
            builder.and(residualThickness.partElementLibraryId.eq(thickness.getPartElementLibraryId()));
        }
        if (thickness.getDiameter() != null) {
            builder.and(residualThickness.diameter.eq(thickness.getDiameter()));
        }
        if (thickness.getThickness() != null) {
            builder.and(residualThickness.thickness.eq(thickness.getThickness()));
        }
        if (thickness.getMinAcceptableThicknessMM() != null) {
            builder.and(residualThickness.minAcceptableThicknessMM.eq(thickness.getMinAcceptableThicknessMM()));
        }
        if (thickness.getMinAcceptableThicknessPercent() != null) {
            builder.and(residualThickness.minAcceptableThicknessPercent.eq(thickness.getMinAcceptableThicknessPercent()));
        }
        if (thickness.getMaxAcceptableThinningMM() != null) {
            builder.and(residualThickness.maxAcceptableThinningMM.eq(thickness.getMaxAcceptableThinningMM()));
        }
        if (thickness.getMaxAcceptableThinningPercent() != null) {
            builder.and(residualThickness.maxAcceptableThinningPercent.eq(thickness.getMaxAcceptableThinningPercent()));
        }
        boolean exists = false;
        if (thickness.getId() == null) {
            exists = new JPAQueryFactory(em).select(residualThickness)
                                            .from(residualThickness)
                                            .where(builder)
                                            .fetchOne() != null;
        } else {
            Long id = new JPAQueryFactory(em).select(residualThickness.id)
                                             .from(residualThickness)
                                             .where(builder)
                                             .fetchOne();
            if (id != null) {
                exists = !thickness.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, thickness.getElementFullName()));
        }
    }
}