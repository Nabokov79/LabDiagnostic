package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.equipmentInspection.NewEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.UpdateEquipmentInspectionDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.EquipmentInspectionMapper;
import ru.nabokovsg.equipment.repository.equipment.EquipmentInspectionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentInspectionServiceImpl implements EquipmentInspectionService {

    private final EquipmentInspectionRepository repository;
    private final EquipmentInspectionMapper mapper;

    @Override
    public ResponseEquipmentInspectionDto save(NewEquipmentInspectionDto inspectionDto) {
        if (repository.existsByEquipmentIdAndDateAndDocumentNumberAndOrganization(inspectionDto.getEquipmentId()
                                                                                , inspectionDto.getDate()
                                                                                , inspectionDto.getDocumentNumber()
                                                                                , inspectionDto.getOrganization())) {
            throw new BadRequestException(
                    String.format("Equipment inspection =%s, is found", inspectionDto));
        }
        return mapper.mapToResponseEquipmentInspectionDto(
                                                repository.save(mapper.mapToEquipmentInspection(inspectionDto)));
    }

    @Override
    public ResponseEquipmentInspectionDto update(UpdateEquipmentInspectionDto inspectionDto) {
        if (repository.existsById(inspectionDto.getId())) {
            return mapper.mapToResponseEquipmentInspectionDto(
                    repository.save(mapper.mapToUpdateEquipmentInspection(inspectionDto)));
        }
        throw new NotFoundException(
                String.format("Equipment inspection with id=%s not found for delete", inspectionDto.getId()));
    }

    @Override
    public List<ResponseEquipmentInspectionDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                         .stream()
                         .map(mapper::mapToResponseEquipmentInspectionDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment inspection with id=%s not found for delete", id));
    }
}