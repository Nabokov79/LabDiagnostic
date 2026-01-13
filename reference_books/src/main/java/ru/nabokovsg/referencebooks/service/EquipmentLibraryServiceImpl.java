package ru.nabokovsg.referencebooks.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.EquipmentLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.QEquipmentLibrary;
import ru.nabokovsg.referencebooks.repository.EquipmentLibraryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentLibraryServiceImpl implements EquipmentLibraryService {

    private final EquipmentLibraryRepository repository;
    private final EquipmentLibraryMapper mapper;
    private final EntityManager em;
    private final static String NO_FOUND = "Оборудование не обнаружено";

    @Override
    public ResponseEquipmentLibraryDto save(NewEquipmentLibraryDto equipmentDto) {
        EquipmentLibrary equipment = mapper.mapToEquipmentLibrary(equipmentDto);
        validByDuplicate(equipment);
        return mapper.mapToResponseEquipmentLibraryDto(repository.save(equipment));
    }

    @Override
    public ResponseEquipmentLibraryDto update(UpdateEquipmentLibraryDto equipmentDto) {
        EquipmentLibrary equipment = getById(equipmentDto.getId());
        mapper.mapToUpdateEquipmentLibrary(equipment, equipmentDto);
        validByDuplicate(equipment);
        return mapper.mapToResponseEquipmentLibraryDto(repository.save(equipment));
    }

    @Override
    public ResponseEquipmentLibraryDto get(Long id) {
        return mapper.mapToResponseEquipmentLibraryDto(getById(id));
    }

    @Override
    public List<ResponseEquipmentLibraryDto> getAll(String name) {
        List<EquipmentLibrary> equipments = repository.findAll();
        if (!equipments.isEmpty() && name != null) {
            final String equipmentName = name.toLowerCase();
            return equipments.stream()
                             .filter(equipment -> equipment.getFullName().toLowerCase().contains(equipmentName)
                                               || equipment.getShortName().toLowerCase().contains(equipmentName))
                             .map(mapper::mapToResponseEquipmentLibraryDto)
                             .toList();
        }
        return equipments.stream()
                         .sorted(Comparator.comparing(EquipmentLibrary::getFullName))
                         .map(mapper::mapToResponseEquipmentLibraryDto)
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
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException(NO_FOUND));
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
}