package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.MetalHardnessLibraryMapper;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.MetalHardnessLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.EquipmentInformationBuilderService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.SizeAcceptableCheckingValidator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetalHardnessLibraryServiceImpl implements MetalHardnessLibraryService {

      private final MetalHardnessLibraryRepository repository;
    private final MetalHardnessLibraryMapper mapper;
    private final SizeAcceptableCheckingValidator validator;
    private final EquipmentInformationBuilderService builderService;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final ToStringService toString;
    private final static String MASSAGE = "Допустимое значение твердости металла не обнаружено.";

    @Override
    public ResponseShortAcceptableMetalHardnessLibraryDto save(NewAcceptableMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary hardness = mapper.mapToAcceptableHardness(hardnessDto);
        build(hardness);
        return mapper.mapToResponseShortAcceptableMetalHardnessLibraryDto(repository.save(hardness));
    }

    @Override
    public ResponseShortAcceptableMetalHardnessLibraryDto update(UpdateAcceptableMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary hardness = getById(hardnessDto.getId());
        mapper.mapToUpdateAcceptableHardness(hardness, hardnessDto);
        build(hardness);
        return mapper.mapToResponseShortAcceptableMetalHardnessLibraryDto(repository.save(hardness));
    }

    @Override
    public ResponseAcceptableMetalHardnessLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MASSAGE)));
    }

    @Override
    public List<ResponseShortAcceptableMetalHardnessLibraryDto> getAll(String name) {
        Set<MetalHardnessLibrary> metalsHardness = repository.findAllOrderByEquipmentFullName();
        if (name != null) {
            final String search = name.toLowerCase();
            metalsHardness = metalsHardness.stream()
                    .filter(hardness ->
                            hardness.getEquipmentFullName().toLowerCase().contains(search)
                                    || hardness.getElementFullName().toLowerCase().contains(search)
                                    || hardness.getDocumentationLibrary().toLowerCase().contains(search))
                    .collect(Collectors.toSet());
        }
        return  metalsHardness.stream()
                .map(mapper::mapToResponseShortAcceptableMetalHardnessLibraryDto)
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

    private MetalHardnessLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void build(MetalHardnessLibrary metalHardness) {
        validator.validateStandardSize(metalHardness.getDiameter(), metalHardness.getThickness());
        validator.validateAcceptableHardness(metalHardness.getMinAcceptableHardness(), metalHardness.getMaxAcceptableHardness());
        Map<String, String> information;
        if (metalHardness.getPartElementLibraryId() != null) {
            information = builderService.getByPartElement(metalHardness.getPartElementLibraryId());
        } else {
            information = builderService.getByElement(metalHardness.getElementLibraryId());
        }
        String documentationLibrary = documentationService.getDocument(metalHardness.getDocumentationLibraryId());
        information.forEach((k,v) ->  mapper.mapToMetalHardnessLibrary(metalHardness, k, v, documentationLibrary
                                                            , toString.getStandardSize(metalHardness.getDiameter()
                                                                                     , metalHardness.getThickness())));
        exists(metalHardness);
    }

    private void exists(MetalHardnessLibrary hardness) {
        MetalHardnessLibrary duplicate;
        if (hardness.getPartElementLibraryId() != null) {
            duplicate = getByPartElementLibraryId(hardness.getPartElementLibraryId());
        } else {
            duplicate = getByElementLibraryId(hardness.getElementLibraryId());
        }
        if (duplicate != null && (hardness.getId() == null || !Objects.equals(duplicate.getId(), hardness.getId()))) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, hardness.getElementFullName()));
        }
    }

    private MetalHardnessLibrary getByElementLibraryId(Long id) {
        return repository.findByElementLibraryId(id);
    }

    private MetalHardnessLibrary getByPartElementLibraryId(Long id) {
        return repository.findByPartElementLibraryId(id);
    }
}