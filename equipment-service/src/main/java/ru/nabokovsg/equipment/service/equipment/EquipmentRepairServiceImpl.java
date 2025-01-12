package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.UpdateEquipmentRepairDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.EquipmentRepairMapper;
import ru.nabokovsg.equipment.repository.equipment.EquipmentRepairRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentRepairServiceImpl implements EquipmentRepairService {

    private final EquipmentRepairRepository repository;
    private final EquipmentRepairMapper mapper;

    @Override
    public ResponseEquipmentRepairDto save(NewEquipmentRepairDto repairDto) {
        if (repository.existsByEquipmentIdAndDateAndDescriptionAndOrganization(repairDto.getEquipmentId()
                                                                             , repairDto.getDate()
                                                                             , repairDto.getDescription()
                                                                             , repairDto.getOrganization())) {
            throw new BadRequestException(String.format("Equipment repair =%s is found", repairDto));
        }
        return mapper.mapToResponseEquipmentRepairDto(repository.save(mapper.mapToEquipmentRepair(repairDto)));
    }

    @Override
    public ResponseEquipmentRepairDto update(UpdateEquipmentRepairDto repairDto) {
        if (repository.existsById(repairDto.getId())) {
            return mapper.mapToResponseEquipmentRepairDto(
                    repository.save(mapper.mapToUpdateEquipmentRepair(repairDto)));
        }
        throw new NotFoundException(
                String.format("Equipment repair with id=%s not found for update", repairDto.getId()));
    }

    @Override
    public List<ResponseEquipmentRepairDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseEquipmentRepairDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment repair with id=%s not found for delete", id));
    }
}
