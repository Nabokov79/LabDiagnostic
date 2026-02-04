package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.NewDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.ResponseDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.UpdateDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.DeviationsGeodesyLibraryMapper;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.repository.DeviationsGeodesyLibraryRepository;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviationsGeodesyLibraryServiceImpl implements DeviationsGeodesyLibraryService {

    private final DeviationsGeodesyLibraryRepository repository;
    private final DeviationsGeodesyLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final ToStringService toString;
    private final static String MASSAGE = "Допустимые отклонения значений геодезических измерений не обнаружены.";

    @Override
    public ResponseDeviationsGeodesyLibraryDto save(NewDeviationsGeodesyLibraryDto geodesyDto) {
        DeviationsGeodesyLibrary geodesyLibrary = mapper.mapToAcceptableDeviationsGeodesy(geodesyDto);
        build(geodesyLibrary);
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(repository.save(geodesyLibrary));
    }

    @Override
    public ResponseDeviationsGeodesyLibraryDto update(UpdateDeviationsGeodesyLibraryDto geodesyDto) {
        DeviationsGeodesyLibrary geodesyLibrary = getById(geodesyDto.getId());
        mapper.mapToUpdateAcceptableDeviationsGeodesy(geodesyLibrary, geodesyDto);
        build(geodesyLibrary);
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(repository.save(geodesyLibrary));
    }

    @Override
    public ResponseDeviationsGeodesyLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(getById(id));
    }

    @Override
    public List<ResponseDeviationsGeodesyLibraryDto> getAll(String name) {
        Set<DeviationsGeodesyLibrary> deviations = repository.findAllOrderByEquipmentLibrary();
        if (name != null) {
            String equipmentLibraryName = name.toLowerCase();
            deviations = deviations.stream()
                    .filter(deviation -> deviation.getEquipmentLibrary().toLowerCase().contains(equipmentLibraryName))
                    .collect(Collectors.toSet());
        }
        return deviations.stream()
                .map(mapper::mapToResponseAcceptableDeviationsGeodesyDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }


    private void build(DeviationsGeodesyLibrary deviationsGeodesy) {
        EquipmentLibrary equipment = equipmentService.getById(deviationsGeodesy.getEquipmentLibraryId());
        mapper.mapWithFields(deviationsGeodesy
                , toString.getEquipmentLibraryFullName(equipment)
                , equipment.getVolume()
                , getHeatCarrier(deviationsGeodesy.getWithHeatCarrier())
                , getEquipmentCondition(deviationsGeodesy.getCondition()));
        exists(deviationsGeodesy);
    }

    private void exists(DeviationsGeodesyLibrary deviation) {
        boolean exists = false;
        if (deviation.getId() == null) {
            exists = repository.existsByEquipmentLibraryIdAndWithHeatCarrierAndCondition(
                    deviation.getEquipmentLibraryId()
                    , deviation.getWithHeatCarrier()
                    , deviation.getCondition());
        } else {
            Long id = repository.findIdByEquipmentLibraryIdAndWithHeatCarrierAndCondition(
                    deviation.getEquipmentLibraryId()
                    , deviation.getWithHeatCarrier()
                    , deviation.getCondition());
            if (id != null) {
                exists = !deviation.getId().equals(id);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("для ", ExceptionMassage.DUPLICATE.label
                    , deviation.getEquipmentLibrary()));
        }
    }

    private String getHeatCarrier(Boolean withHeatCarrier) {
        if (withHeatCarrier) {
            return EquipmentCondition.WITH_HEAT_CARRIER.label;
        }
        return EquipmentCondition.WITHOUT_HEAT_CARRIER.label;
    }

    private String getEquipmentCondition(Boolean condition) {
        if (condition) {
            return EquipmentCondition.NEW.label;
        }
        return EquipmentCondition.OLD.label;
    }

    public DeviationsGeodesyLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MASSAGE));
    }
}