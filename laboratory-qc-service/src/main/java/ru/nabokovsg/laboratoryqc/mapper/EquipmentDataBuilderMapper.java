package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

@Mapper(componentModel = "spring")
public interface EquipmentDataBuilderMapper {
    void mapToEquipment(@MappingTarget JournalCompletedWork journal, String equipmentName);
}