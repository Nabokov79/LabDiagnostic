package ru.nabokovsg.referencebooks.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseShortEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.EquipmentLibraryMapper;
import ru.nabokovsg.referencebooks.repository.EquipmentLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.CopyEquipmentElementsService;
import ru.nabokovsg.referencebooks.service_factory.ElementNameFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentLibraryServiceImpl implements EquipmentLibraryService {

    private final EquipmentLibraryRepository repository;
    private final EquipmentLibraryMapper mapper;
    private final ElementNameFactory factory;
    private final EntityManager em;
    private final CopyEquipmentElementsService copyService;
    private final static String NO_FOUND = "Оборудование не обнаружено";

    @Override
    public ResponseShortEquipmentLibraryDto save(NewEquipmentLibraryDto equipmentDto) {
        EquipmentLibrary equipment = mapper.mapToEquipmentLibrary(equipmentDto);
        build(equipment);
        return mapper.mapToResponseShortEquipmentLibraryDto(repository.save(equipment));
    }

    @Override
    public ResponseShortEquipmentLibraryDto update(UpdateEquipmentLibraryDto equipmentDto) {
        EquipmentLibrary equipment = getById(equipmentDto.getId());
        mapper.mapToUpdateEquipmentLibrary(equipment, equipmentDto);
        build(equipment);
        return mapper.mapToResponseShortEquipmentLibraryDto(repository.save(equipment));
    }

    @Override
    public List<ResponseShortElementLibraryDto> copyElements(Long id, Long copyId) {
        Map<Long, EquipmentLibrary> equipments = repository.findAllById(List.of(id, copyId))
                                            .stream()
                                            .collect(Collectors.toMap(EquipmentLibrary::getId, equipment -> equipment));
        return copyService.copyElements(equipments.get(id), equipments.get(copyId));
    }

    @Override
    public ResponseEquipmentLibraryDto get(Long id) {
        return mapper.mapToResponseEquipmentLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortEquipmentLibraryDto> getAll(String name) {
        List<EquipmentLibrary> equipments = repository.findAll();
        if (!equipments.isEmpty() && name != null) {
            final String equipmentName = name.toLowerCase();
            return equipments.stream()
                             .filter(equipment -> equipment.getFullName().toLowerCase().contains(equipmentName)
                                               || equipment.getShortName().toLowerCase().contains(equipmentName)
                                               || equipment.getModel().toLowerCase().contains(equipmentName)
                                               || String.valueOf(equipment.getVolume()).contains(equipmentName))
                             .map(mapper::mapToResponseShortEquipmentLibraryDto)
                             .toList();
        }
        return equipments.stream()
                         .sorted(Comparator.comparing(EquipmentLibrary::getFullName))
                         .map(mapper::mapToResponseShortEquipmentLibraryDto)
                         .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NO_FOUND);
    }

    @Override
    public EquipmentLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NO_FOUND));
    }

    @Override
    public Set<EquipmentLibrary> getAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    private void build(EquipmentLibrary equipment) {
        validByDuplicate(equipment);
        mapper.mapToDimensions(equipment
                , getEquipmentFullName(equipment)
                , factory.createDimensions(equipment.getDiameter(), equipment.getLength(), equipment.getHeight(), equipment.getWidth()));
    }

    private void validByDuplicate(EquipmentLibrary equipment) {
        QEquipmentLibrary equipmentLibrary = QEquipmentLibrary.equipmentLibrary;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(equipmentLibrary.fullName.eq(equipment.getFullName()));
        if (equipment.getVolume() != null) {
            builder.and(equipmentLibrary.volume.eq(equipment.getVolume()));
        }
        if (equipment.getModel() != null) {
            builder.and(equipmentLibrary.model.eq(equipment.getModel()));
        }
        if (new JPAQueryFactory(em).select(equipmentLibrary)
                                   .from(equipmentLibrary)
                                   .where(builder)
                                   .fetchOne() != null) {
            throw new BadRequestException(
                                String.join(" ", ExceptionMassage.DUPLICATE.label, equipment.getFullName()));
        }
    }

    private String getEquipmentFullName(EquipmentLibrary equipment) {
        String volume = null;
        if (equipment.getVolume() != null) {
            volume = String.join("", "V=", String.valueOf(equipment.getVolume()), " м3");
        }
        if (volume != null) {
            return String.join("", equipment.getFullName(), ", ", volume);
        }
        if (equipment.getModel() != null) {
            return String.join("", equipment.getFullName(), ", ", equipment.getModel());
        }
        return equipment.getFullName();
    }
}