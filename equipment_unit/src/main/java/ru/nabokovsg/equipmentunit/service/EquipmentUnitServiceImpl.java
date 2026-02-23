package ru.nabokovsg.equipmentunit.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipmentunit.dto.equipment.*;
import ru.nabokovsg.equipmentunit.exceptions.BadRequestException;
import ru.nabokovsg.equipmentunit.exceptions.NotFoundException;
import ru.nabokovsg.equipmentunit.mapper.EquipmentUnitMapper;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;
import ru.nabokovsg.equipmentunit.model.QEquipmentUnit;
import ru.nabokovsg.equipmentunit.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.equipmentunit.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.equipmentunit.repository.EquipmentUnitRepository;
import ru.nabokovsg.equipmentunit.search.SearchService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentUnitServiceImpl implements EquipmentUnitService {

    private final EquipmentUnitRepository repository;
    private final EquipmentUnitMapper mapper;
    private final EntityManager em;
    private final StructureOrganizationService structureService;
    private final SearchService searchService;

    @Override
    public ResponseEquipmentUnitDto save(NewEquipmentUnitDto equipmentDto) {
        EquipmentUnit equipment = mapper.mapToEquipmentUnit(equipmentDto);
        build(equipment
            , equipment.getStructure().getHeatSupplySourceId()
            , equipment.getStructure().getTechnicalDeviceId());
        return mapper.mapToResponseEquipmentUnitDto(repository.save(equipment));
    }

    @Override
    public ResponseEquipmentUnitDto update(UpdateEquipmentUnitDto equipmentDto) {
        EquipmentUnit equipment = getById(equipmentDto.getId());
        mapper.mapToUpdateEquipmentUnit(equipment, equipmentDto);
        build(equipment
            , equipment.getStructure().getHeatSupplySourceId()
            , equipment.getStructure().getTechnicalDeviceId());
        return mapper.mapToResponseEquipmentUnitDto(repository.save(equipment));
    }

    @Override
    public ResponseEquipmentUnitDto get(Long id) {
        return mapper.mapToResponseEquipmentUnitDto(getById(id));
    }

    @Override
    public List<ResponseEquipmentUnitSourceDto> getAllEquipmentUnitSource(String search) {
        return filterEquipmentUnits(search, repository.findAllEquipmentUnitSource())
                                                            .stream()
                                                            .map(mapper::mapToResponseEquipmentUnitSourceDto)
                                                            .toList();
    }

    @Override
    public List<ResponseEquipmentUnitDeviceDto> getAllEquipmentUnitDevice(String search) {
        return filterEquipmentUnits(search, repository.findAllEquipmentUnitDevice())
                                                             .stream()
                                                             .map(mapper::mapToResponseEquipmentUnitDeviceDto)
                                                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.EQUIPMENT_UNIT.label);
    }

    @Override
    public EquipmentUnit getById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.EQUIPMENT_UNIT.label));
    }

    private void build(EquipmentUnit equipment, Long sourceId, Long deviceId) {
        if (exists(equipment, sourceId)) {
            throw new BadRequestException(
                    String.join(" ", BadRequestExceptionMassage.DUPLICATE.label, equipment.getFullName()));
        }
        mapper.mapWithStructureOrganization(equipment, structureService.getStructureOrganization(sourceId, deviceId));
    }

    private boolean exists(EquipmentUnit equipment, Long sourceId) {
        QEquipmentUnit equipmentUnit = QEquipmentUnit.equipmentUnit;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(equipmentUnit.equipmentLibraryId.eq(equipment.getEquipmentLibraryId()));
        builder.and(equipmentUnit.structure.heatSupplySourceId.eq(sourceId));
        if (equipment.getStationaryNumber() != null) {
            builder.and(equipmentUnit.stationaryNumber.eq(equipment.getStationaryNumber()));
        }
        if (equipment.getRoom() != null) {
            builder.and(equipmentUnit.room.eq(equipment.getRoom()));
        }
        return new JPAQueryFactory(em).select(equipmentUnit)
                                      .from(equipmentUnit)
                                      .where(builder)
                                      .fetchOne() != null;
    }


    private Set<EquipmentUnit> filterEquipmentUnits(String search, Set<EquipmentUnit> equipments) {
        if (search == null) {
            return equipments;
        }
        return equipments.stream()
                .filter(equipment -> searchService.search(search, getSearchList(equipment)))
                .collect(Collectors.toSet());
    }

    private List<String> getSearchList(EquipmentUnit equipment) {
        return List.of(equipment.getStructure().getDepartment()
                , equipment.getStructure().getHeatSupplySource()
                , equipment.getStructure().getHeatSupplySite()
                , equipment.getStructure().getTechnicalDevice()
                , equipment.getFullName()
                , equipment.getRoom());
    }
}