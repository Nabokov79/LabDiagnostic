package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.AcceptableResidualThicknessLibraryMapper;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.ResidualThicknessLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.ElementNameFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidualThicknessLibraryServiceImpl implements ResidualThicknessLibraryService {

    private final ResidualThicknessLibraryRepository repository;
    private final AcceptableResidualThicknessLibraryMapper mapper;
    private final ElementNameFactory elementNameFactory;
    private final static String MASSAGE = "Допустимые значения остаточной толщины не обнаружены.";

    @Override
    public ResponseResidualThicknessLibraryDto save(NewResidualThicknessLibraryDto thicknessDto) {
        ResidualThicknessLibrary thickness = mapper.mapToAcceptableThickness(thicknessDto
                                                  , elementNameFactory.create(thicknessDto.getElementLibraryId()
                                                                            , thicknessDto.getPartElementLibraryId()));
        validate(thickness);
        searchDuplicate(thickness);
        return mapper.mapToResponseAcceptableResidualThicknessDto(repository.save(thickness));
    }

    @Override
    public ResponseResidualThicknessLibraryDto update(UpdateResidualThicknessLibraryDto thicknessDto) {
        ResidualThicknessLibrary thickness = getById(thicknessDto.getId());
        mapper.mapToUpdateAcceptableThickness(thickness, thicknessDto);
        validate(thickness);
        return mapper.mapToResponseAcceptableResidualThicknessDto(repository.save(thickness));
    }

    @Override
    public ResponseResidualThicknessLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableResidualThicknessDto(getById(id));
    }

    @Override
    public List<ResponseResidualThicknessLibraryDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryIdOrderByElementNameDesc(equipmentLibraryId)
                         .stream()
                         .map(mapper::mapToResponseAcceptableResidualThicknessDto)
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

    private void validate(ResidualThicknessLibrary thickness) {
        if (thickness.getAcceptableThickness() == null && thickness.getAcceptablePercent() == null) {
            throw new BadRequestException("Не задано одно из значений допустимой толщины.");
        }
        if (thickness.getAcceptableThickness() != null && thickness.getAcceptableThickness() <= 0) {
            throw new BadRequestException(
                    String.format("Толщина может быть только положительным значением: %s", thickness.getAcceptableThickness()));
        }
        if (thickness.getAcceptablePercent() != null && thickness.getAcceptablePercent() <= 0) {
            throw new BadRequestException(
                    String.format("Процент может быть только положительным значением: %s", thickness.getAcceptablePercent()));
        }
    }

    private void searchDuplicate(ResidualThicknessLibrary thickness) {
        ResidualThicknessLibrary residualThickness;
        if (thickness.getPartElementLibraryId() != null) {
            residualThickness = getByPartElementLibraryId(thickness.getPartElementLibraryId(), thickness.getStandardSize());
        } else {
            residualThickness = getByElementLibraryId(thickness.getElementLibraryId(), thickness.getStandardSize());
        }
        if (residualThickness != null) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, thickness.getElementName()));
        }
    }

    private ResidualThicknessLibrary getByElementLibraryId(Long id, Double standardSize) {
        return repository.findByElementLibraryIdAndStandardSize(id, standardSize);
    }

    private ResidualThicknessLibrary getByPartElementLibraryId(Long id, Double standardSize) {
        return repository.findByPartElementLibraryIdAndStandardSize(id, standardSize);
    }
}