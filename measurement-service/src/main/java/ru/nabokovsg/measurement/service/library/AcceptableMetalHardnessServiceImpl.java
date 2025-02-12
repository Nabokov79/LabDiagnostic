package ru.nabokovsg.measurement.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.NewAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.ResponseAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.UpdateAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.AcceptableMetalHardnessMapper;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;
import ru.nabokovsg.measurement.repository.library.AcceptableMetalHardnessRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcceptableMetalHardnessServiceImpl implements AcceptableMetalHardnessService {

    private final AcceptableMetalHardnessRepository repository;
    private final AcceptableMetalHardnessMapper mapper;

    @Override
    public ResponseAcceptableMetalHardnessDto save(NewAcceptableMetalHardnessDto hardnessDto) {
        AcceptableMetalHardness acceptableMetalHardness = mapper.mapToAcceptableHardness(hardnessDto
                                                          , getStandardSize(hardnessDto.getMinAcceptableDiameter()
                                                                          , hardnessDto.getMinAcceptableThickness()));
        if (getDuplicate(acceptableMetalHardness)) {
            throw new BadRequestException(
                    String.format("AcceptableHardness for hardness=%s is found", hardnessDto));
        }
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.save(acceptableMetalHardness));
    }

    @Override
    public ResponseAcceptableMetalHardnessDto update(UpdateAcceptableMetalHardnessDto hardnessDto) {
        if (repository.existsById(hardnessDto.getId())) {
            return mapper.mapToResponseAcceptableMetalHardnessDto(
                    repository.save(mapper.mapToUpdateAcceptableHardness(hardnessDto
                                                           , getStandardSize(hardnessDto.getMinAcceptableDiameter()
                                                                          , hardnessDto.getMinAcceptableThickness()))));
        }
        throw new NotFoundException(
                String.format("AcceptableHardness with id=%s not found for update", hardnessDto.getId())
        );
    }

    @Override
    public ResponseAcceptableMetalHardnessDto get(Long id) {
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("AcceptableHardness with id=%s not found", id))));
    }

    @Override
    public List<ResponseAcceptableMetalHardnessDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryId(equipmentLibraryId)
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
        throw new NotFoundException(String.format("AcceptableHardness with id=%s not found for delete", id));
    }


    private boolean getDuplicate(AcceptableMetalHardness acceptableMetalHardness) {
        if (acceptableMetalHardness.getPartElementLibraryId() != null) {
            return repository.existsByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                      acceptableMetalHardness.getEquipmentLibraryId()
                                                                    , acceptableMetalHardness.getElementLibraryId()
                                                                    , acceptableMetalHardness.getPartElementLibraryId()
                                                                    , acceptableMetalHardness.getStandardSize());
        }
        return repository.existsByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(
                                                                  acceptableMetalHardness.getEquipmentLibraryId()
                                                                , acceptableMetalHardness.getElementLibraryId()
                                                                , acceptableMetalHardness.getStandardSize());
    }

    private String getStandardSize(Integer diameter, Double thickness) {
        String standardSize = String.valueOf(diameter);
        if (thickness != null && thickness <= 0 ) {
            throw new BadRequestException(
                    String.format("Thickness can only be positive: thickness=%s", thickness));
        }
        if(thickness == null) {
            return standardSize;
        }
        return String.join("x", standardSize, String.valueOf(thickness));
    }
}