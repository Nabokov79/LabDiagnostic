package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.AcceptableMetalHardnessLibraryMapper;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.MetalHardnessLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.ElementNameFactory;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MetalHardnessLibraryServiceImpl implements MetalHardnessLibraryService {

    private final MetalHardnessLibraryRepository repository;
    private final AcceptableMetalHardnessLibraryMapper mapper;
    private final ElementNameFactory elementNameFactory;
    private final static String MASSAGE = "Допустимое значение твердости металла не обнаружено.";

    @Override
    public ResponseAcceptableMetalHardnessLibraryDto save(NewAcceptableMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary hardness = mapper.mapToAcceptableHardness(hardnessDto,
                elementNameFactory.create(hardnessDto.getElementLibraryId(), hardnessDto.getPartElementLibraryId()));
        validate(hardness);
        searchDuplicate(hardness);
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.save(hardness));
    }

    @Override
    public ResponseAcceptableMetalHardnessLibraryDto update(UpdateAcceptableMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary hardness = getById(hardnessDto.getId());
        mapper.mapToUpdateAcceptableHardness(hardness, hardnessDto);
        validate(hardness);
        searchDuplicate(hardness);
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.save(hardness));
    }

    @Override
    public ResponseAcceptableMetalHardnessLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MASSAGE)));
    }

    @Override
    public List<ResponseAcceptableMetalHardnessLibraryDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryIdOrderByElementNameDesc(equipmentLibraryId)
                .stream()
                .map(mapper::mapToResponseAcceptableMetalHardnessDto)
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

    private MetalHardnessLibrary getByElementLibraryId(Long id) {
        return repository.findByElementLibraryId(id);
    }

    private MetalHardnessLibrary getByPartElementLibraryId(Long id) {
        return repository.findByPartElementLibraryId(id);
    }

    private void searchDuplicate(MetalHardnessLibrary hardness) {
        MetalHardnessLibrary duplicate;
        if (hardness.getPartElementLibraryId() != null) {
            duplicate = getByPartElementLibraryId(hardness.getPartElementLibraryId());
        } else {
            duplicate = getByElementLibraryId(hardness.getElementLibraryId());
        }
        if (duplicate != null && (hardness.getId() == null || !Objects.equals(duplicate.getId(), hardness.getId()))) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, hardness.getElementName()));
        }
    }

    private void validate(MetalHardnessLibrary hardness) {
        if (hardness.getMinAcceptableDiameter() == null && hardness.getMinAcceptableThickness() == null) {
            throw new BadRequestException(
                    String.format("Не задано одно из значений: minAcceptableDiameter=%s, minAcceptableThickness=%s"
                            , hardness.getMinAcceptableDiameter(), hardness.getMinAcceptableThickness()));
        }
        if (hardness.getMinAcceptableDiameter() != null && hardness.getMinAcceptableDiameter() <= 0) {
            throw new BadRequestException(String.format("Минимальный диаметр может быть только положительным значением" +
                    ": %s", hardness.getMinAcceptableDiameter()));
        }
        if (hardness.getMinAcceptableThickness() != null && hardness.getMinAcceptableThickness() <= 0) {
            throw new BadRequestException(String.format("Минимальная толщина может быть только положительным значением" +
                    ": %s", hardness.getMinAcceptableThickness()));
        }
    }
}