package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.equipment.NewEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseShortEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.UpdateEquipmentDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.EquipmentMapper;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.repository.equipment.EquipmentRepository;
import ru.nabokovsg.equipment.service.library.EquipmentLibraryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;
    private final EquipmentLibraryService equipmentLibraryService;

    @Override
    public ResponseEquipmentDto save(NewEquipmentDto equipmentDto) {
        Equipment equipment = mapper.mapToEquipment(equipmentDto
                , equipmentLibraryService.getById(equipmentDto.getEquipmentLibraryId()));
        valid(equipment);
        if (getByPredicate(equipment)) {
            throw new BadRequestException(String.format("Equipment found: %s", equipmentDto));
        }
        return mapper.mapToResponseEquipmentDto(repository.save(equipment));
    }

    @Override
    public ResponseEquipmentDto update(UpdateEquipmentDto equipmentDto) {
        if (repository.existsById(equipmentDto.getId())) {
            Equipment equipment = mapper.mapToUpdateEquipment(equipmentDto, equipmentLibraryService.getById(equipmentDto.getEquipmentLibraryId()));
            valid(equipment);
            return mapper.mapToResponseEquipmentDto(repository.save(equipment));
        }
        throw new NotFoundException(String.format("Equipment not found for update: %s", equipmentDto));
    }

    @Override
    public ResponseEquipmentDto get(Long id) {
        return mapper.mapToResponseEquipmentDto(getById(id));
    }

    @Override
    public List<ResponseShortEquipmentDto> getAll(Long buildingId) {
        return repository.findAllByBuildingId(buildingId).stream()
                .map(mapper::mapToResponseShortEquipmentDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment with id=%s not found for delete", id));
    }

    @Override
    public Equipment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Equipment with id=%s not found", id)));
    }

    private boolean getByPredicate(Equipment equipment) {
        if (equipment.getRoom() != null) {
            return repository.existsByEquipmentLibraryIdAndAddressIdAndRoom(equipment.getEquipmentLibraryId()
                                                                           , equipment.getAddressId()
                                                                           , equipment.getRoom());
        }
        return repository.existsByEquipmentLibraryIdAndAddressId(equipment.getEquipmentLibraryId()
                                                                , equipment.getAddressId());
    }

    private void valid(Equipment equipment) {
        if (equipment.getBuildingId() != null && equipment.getBuildingId() <= 0) {
            throw new BadRequestException(
                    String.format("building id an only be positive: %s", equipment.getBuildingId()));
        }
        if (equipment.getRoom() != null && equipment.getRoom().isBlank()) {
            throw new BadRequestException(
                    String.format("room should not be blank: %s", equipment.getRoom()));
        }

        if (equipment.getGeodesyLocations() != null && equipment.getGeodesyLocations() <= 0) {
            throw new BadRequestException(
                    String.format("geodesyLocations an only be positive: %s", equipment.getGeodesyLocations()));
        }
    }
}