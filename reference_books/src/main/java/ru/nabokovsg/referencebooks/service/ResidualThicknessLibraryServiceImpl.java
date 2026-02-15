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
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.model.QResidualThicknessLibrary;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.ResidualThicknessLibraryRepository;
import ru.nabokovsg.referencebooks.search.SearchService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.SizeAcceptableCheckingValidator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidualThicknessLibraryServiceImpl implements ResidualThicknessLibraryService {

    private final ResidualThicknessLibraryRepository repository;
    private final ResidualThicknessLibraryMapper mapper;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final SizeAcceptableCheckingValidator validator;
    private final EntityManager em;
    private final ToStringService toString;
    private final SearchService searchService;
    private final ElementLibraryService elementService;
    private final PartElementLibraryService partElementService;
    private final static String MASSAGE = "Допустимые значения остаточной толщины не обнаружены.";

    @Override
    public ResponseShortResidualThicknessLibraryDto save(NewResidualThicknessLibraryDto residualThicknessDto) {
        ResidualThicknessLibrary residualThickness = mapper.mapToResidualThickness(residualThicknessDto);
        build(residualThickness, residualThicknessDto.getElementId()
                , residualThicknessDto.getPartElementId(), residualThicknessDto.getDocumentationId());
        return getResponseShortMetalHardnessLibraryDto(repository.save(residualThickness));
    }

    @Override
    public ResponseShortResidualThicknessLibraryDto update(UpdateResidualThicknessLibraryDto residualThicknessDto) {
        ResidualThicknessLibrary residualThickness = getById(residualThicknessDto.getId());
        mapper.mapToUpdateResidualThickness(residualThickness, residualThicknessDto);
        build(residualThickness, residualThicknessDto.getElementId()
                , residualThicknessDto.getPartElementId(), residualThicknessDto.getDocumentationId());
        return getResponseShortMetalHardnessLibraryDto(repository.save(residualThickness));
    }

    @Override
    public ResponseResidualThicknessLibraryDto get(Long id) {
        return getResponseMetalHardnessLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortResidualThicknessLibraryDto> getAll(String search) {
        Set<ResidualThicknessLibrary> thicknesses = repository.findAllOrderByEquipmentFullName();
        if (search != null) {
            thicknesses = thicknesses.stream()
                                     .filter(thickness -> searchService.search(search, getSearchList(thickness)))
                                     .collect(Collectors.toSet());
        }
        return thicknesses.stream()
                .map(this::getResponseShortMetalHardnessLibraryDto)
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

    private void build(ResidualThicknessLibrary residualThickness, Long elementId, Long partElementId, Long documentationId) {
        validate(residualThickness);
        if (partElementId != null) {
            PartElementLibrary partElement = partElementService.getById(partElementId);
            mapper.mapWithPartElement(residualThickness
                    , partElement.getElement()
                    , partElement
                    , documentationService.getById(documentationId)
                    , toString.getStandardSize(residualThickness.getDiameter(), residualThickness.getThickness()));
        } else {
            mapper.mapWithElement(residualThickness
                    , elementService.getById(elementId)
                    , documentationService.getById(documentationId)
                    , toString.getStandardSize(residualThickness.getDiameter(), residualThickness.getThickness()));
        }
        exists(residualThickness);
    }

    private void exists(ResidualThicknessLibrary thickness) {
        QResidualThicknessLibrary residualThickness = QResidualThicknessLibrary.residualThicknessLibrary;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(residualThickness.documentation.id.eq(thickness.getDocumentation().getId()));
        if (thickness.getElement() != null) {
            builder.and(residualThickness.element.id.eq(thickness.getElement().getId()));
        }
        if (thickness.getPartElement() != null) {
            builder.and(residualThickness.partElement.id.eq(thickness.getPartElement().getId()));
        }
        if (thickness.getDiameter() != null) {
            builder.and(residualThickness.diameter.eq(thickness.getDiameter()));
        }
        if (thickness.getThickness() != null) {
            builder.and(residualThickness.thickness.eq(thickness.getThickness()));
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
                    String.join("", ExceptionMassage.DUPLICATE.label, getElementFullName(thickness)));
        }
    }

    private List<String> getSearchList(ResidualThicknessLibrary thickness) {
        if (thickness.getPartElement() != null) {
            return List.of(thickness.getPartElement().getElement().getEquipment().getEquipmentFullName()
                    , thickness.getPartElement().getPartElementFullName()
                    , thickness.getDocumentation().getDocument());
        }
        return List.of(thickness.getElement().getEquipment().getEquipmentFullName()
                , thickness.getElement().getName()
                , thickness.getDocumentation().getDocument());
    }

    private String getElementFullName(ResidualThicknessLibrary thickness) {
        if (thickness.getPartElement() != null) {
            return thickness.getPartElement().getPartElementFullName();
        }
        return thickness.getElement().getName();
    }

    private ResponseResidualThicknessLibraryDto getResponseMetalHardnessLibraryDto(ResidualThicknessLibrary thickness) {
        if (thickness.getPartElement() != null) {
            return mapper.mapToResponseResidualThicknessDtoByPartElement(thickness);
        }
        return mapper.mapToResponseResidualThicknessDtoByElement(thickness);
    }

    private ResponseShortResidualThicknessLibraryDto getResponseShortMetalHardnessLibraryDto(ResidualThicknessLibrary thickness) {
        if (thickness.getPartElement() != null) {
            return mapper.mapToResponseShortResidualThicknessLibraryDtoByByPartElement(thickness);
        }
        return mapper.mapToResponseShortResidualThicknessLibraryDtoByByElement(thickness);
    }

    private void validate(ResidualThicknessLibrary residualThickness) {
        validator.validateStandardSize(residualThickness.getDiameter(), residualThickness.getThickness());
        validator.validateResidualThickness(residualThickness.getMinAcceptableThicknessMM()
                                          , residualThickness.getMinAcceptableThicknessPercent()
                                          , residualThickness.getMaxAcceptableThinningMM()
                                          , residualThickness.getMaxAcceptableThinningPercent());
    }
}