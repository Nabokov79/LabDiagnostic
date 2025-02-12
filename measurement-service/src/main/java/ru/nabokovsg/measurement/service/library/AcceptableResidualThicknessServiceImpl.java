package ru.nabokovsg.measurement.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.NewAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.ResponseAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.UpdateAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.AcceptableResidualThicknessMapper;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;
import ru.nabokovsg.measurement.repository.library.AcceptableResidualThicknessRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcceptableResidualThicknessServiceImpl implements AcceptableResidualThicknessService {

    private final AcceptableResidualThicknessRepository repository;
    private final AcceptableResidualThicknessMapper mapper;

    @Override
    public ResponseAcceptableResidualThicknessDto save(NewAcceptableResidualThicknessDto thicknessDto) {
        AcceptableResidualThickness acceptableResidualThickness =
                mapper.mapToAcceptableThickness(thicknessDto
                        , getStandardSize(thicknessDto.getThickness(), thicknessDto.getDiameter()));
        if (getDuplicate(acceptableResidualThickness)) {
            throw new BadRequestException(
                    String.format("AcceptableResidualThickness thickness=%s is found", thicknessDto));
        }
        return mapper.mapToResponseAcceptableResidualThicknessDto(repository.save(acceptableResidualThickness));
    }

    @Override
    public ResponseAcceptableResidualThicknessDto update(UpdateAcceptableResidualThicknessDto thicknessDto) {
        if (repository.existsById(thicknessDto.getId())) {
            AcceptableResidualThickness acceptableResidualThickness =
                    mapper.mapToUpdateAcceptableThickness(thicknessDto
                            , getStandardSize(thicknessDto.getThickness(), thicknessDto.getDiameter()));
            return mapper.mapToResponseAcceptableResidualThicknessDto(repository.save(acceptableResidualThickness));
        }
        throw new NotFoundException(
                String.format("AcceptableResidualThickness with id=%s not found for update", thicknessDto.getId())
        );
    }

    @Override
    public ResponseAcceptableResidualThicknessDto get(Long id) {
        return mapper.mapToResponseAcceptableResidualThicknessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("AcceptableThickness with id=%s not found", id))));
    }

    @Override
    public List<ResponseAcceptableResidualThicknessDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryId(equipmentLibraryId)
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
        throw new NotFoundException(String.format("AcceptableThickness with id=%s not found for delete", id));
    }

    private boolean getDuplicate(AcceptableResidualThickness acceptableThickness) {
        if (acceptableThickness.getPartElementLibraryId() != null) {
            return repository.existsByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                      acceptableThickness.getEquipmentLibraryId()
                                                                    , acceptableThickness.getElementLibraryId()
                                                                    , acceptableThickness.getPartElementLibraryId()
                                                                    , acceptableThickness.getStandardSize());
        }
        return repository.existsByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(
                                                                          acceptableThickness.getEquipmentLibraryId()
                                                                        , acceptableThickness.getElementLibraryId()
                                                                        , acceptableThickness.getStandardSize());
    }

    private String getStandardSize(Double thickness, Integer diameter) {
        if (thickness == null && diameter == null) {
            throw new BadRequestException(
                    String.format("diameter and thickness should not be null:" +
                            " diameter=%s, thickness=%s", diameter, thickness));
        }
        String standardSize = "";
        if (thickness != null && thickness <= 0) {
            throw new BadRequestException(String.format("thickness can only be positive: thickness=%s", thickness));
        }
        if (diameter != null && diameter <= 0) {
            throw new BadRequestException(String.format("diameter can only be positive: diameter=%s", diameter));
        }
        if (diameter != null) {
            standardSize = String.valueOf(diameter);
        }
        if (thickness != null) {
            String thicknessString = String.valueOf(thickness);
            if (!standardSize.isBlank()) {
                return String.join("х", standardSize, thicknessString);
            }
        }
        return standardSize;
    }
}