package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.element.NewElementDto;
import ru.nabokovsg.equipment.dto.element.ResponseElementDto;
import ru.nabokovsg.equipment.dto.element.UpdateElementDto;
import ru.nabokovsg.equipment.model.equipment.Element;

import java.util.List;

public interface ElementService {

    ResponseElementDto save(NewElementDto elementDto);

    ResponseElementDto update(UpdateElementDto elementDto);

    Element get(Long id);

    List<ResponseElementDto> getAll(Long equipmentId);

    void delete(Long id);
}