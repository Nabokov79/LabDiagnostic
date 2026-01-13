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

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviationsGeodesyLibraryServiceImpl implements DeviationsGeodesyLibraryService {

    private final DeviationsGeodesyLibraryRepository repository;
    private final DeviationsGeodesyLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final static String MASSAGE = "Допустимые отклонения значений геодезических измерений не обнаружены.";

    @Override
    public ResponseDeviationsGeodesyLibraryDto save(NewDeviationsGeodesyLibraryDto geodesyDto) {
        DeviationsGeodesyLibrary geodesyLibrary = mapper.mapToAcceptableDeviationsGeodesy(geodesyDto
                                                    , getHeatCarrier(geodesyDto.getWithHeatCarrier())
                                                    , getEquipmentCondition(geodesyDto.getCondition())
                                                    , equipmentService.getById(geodesyDto.getEquipmentLibraryId()));
        getDuplicate(geodesyLibrary);
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(repository.save(geodesyLibrary));
    }


    @Override
    public ResponseDeviationsGeodesyLibraryDto update(UpdateDeviationsGeodesyLibraryDto geodesyDto) {
        DeviationsGeodesyLibrary geodesyLibrary = getById(geodesyDto.getId());
        mapper.mapToUpdateAcceptableDeviationsGeodesy(geodesyLibrary, geodesyDto);
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(repository.save(geodesyLibrary));
    }

    @Override
    public ResponseDeviationsGeodesyLibraryDto get(Long id) {
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(getById(id));
    }

    @Override
    public List<ResponseDeviationsGeodesyLibraryDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryIdOrderByHeatCarrier(equipmentLibraryId)
                         .stream()
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

    private void getDuplicate(DeviationsGeodesyLibrary geodesyLibrary) {
        if (repository.existsByEquipmentLibraryIdAndHeatCarrierAndEquipmentCondition(
                                                                      geodesyLibrary.getEquipmentLibraryId()
                                                                    , geodesyLibrary.getHeatCarrier()
                                                                    , geodesyLibrary.getEquipmentCondition())) {
            throw new BadRequestException("Обнаружен дубликат");
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