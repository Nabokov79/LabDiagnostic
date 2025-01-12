package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.remarkSpecialist.NewRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.ResponseRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.UpdateRemarkSpecialistDto;

import java.util.List;

public interface RemarkSpecialistService {

    ResponseRemarkSpecialistDto save(NewRemarkSpecialistDto remarkDto);

    ResponseRemarkSpecialistDto update(UpdateRemarkSpecialistDto remarkDto);

    List<ResponseRemarkSpecialistDto> getAll(Long equipmentId);

    void delete(Long id);
}