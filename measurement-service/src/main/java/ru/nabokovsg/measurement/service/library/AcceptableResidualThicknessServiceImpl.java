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
        validateAcceptableStandards(thicknessDto.getAcceptableThickness(), thicknessDto.getAcceptablePercent());
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
        validateAcceptableStandards(thicknessDto.getAcceptableThickness(), thicknessDto.getAcceptablePercent());
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
            if (acceptableThickness.getDiameter() == null && acceptableThickness.getThickness() == null) {
                return repository.existsByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                      acceptableThickness.getEquipmentLibraryId()
                                                                    , acceptableThickness.getElementLibraryId()
                                                                    , acceptableThickness.getPartElementLibraryId());
            }
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

    private void validateAcceptableStandards(Double acceptableThickness, Integer acceptablePercent) {
        if (acceptableThickness == null &&  acceptablePercent == null) {
            throw new BadRequestException(
                    String.format("There are no standards: acceptableThickness=%s, acceptablePercent=%s"
                                                                , acceptableThickness, acceptablePercent));
        }
        if (acceptableThickness != null && acceptableThickness <= 0) {
            throw new BadRequestException(
                    String.format("acceptableThickness can only be positive: %s", acceptableThickness));
        }
        if (acceptablePercent != null && acceptablePercent <= 0) {
            throw new BadRequestException(
                    String.format("acceptablePercent can only be positive: %s", acceptablePercent));
        }
    }

    private String getStandardSize(Double thickness, Integer diameter) {
        if (thickness == null && diameter == null) {
           return null;
        }
        String standardSize;
        if (diameter != null && diameter <= 0) {
            throw new BadRequestException(String.format("diameter can only be positive: %s", diameter));
        } else {
            standardSize = String.valueOf(diameter);
        }
        if (thickness != null && thickness <= 0) {
            throw new BadRequestException(String.format("thickness can only be positive: %s", thickness));
        } else {
            String thicknessString = String.valueOf(thickness);
            if (!standardSize.isBlank()) {
                return String.join("х", standardSize, thicknessString);
            } else {
                return thicknessString;
            }
        }
    }
}