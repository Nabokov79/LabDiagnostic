package ru.nabokovsg.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.building.NewBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseShortBuildingDto;
import ru.nabokovsg.company.dto.building.UpdateBuildingDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mapper.BuildingMapper;
import ru.nabokovsg.company.model.Building;
import ru.nabokovsg.company.repository.BuildingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl extends ConstantBuildingType implements BuildingService {

    private final BuildingRepository repository;
    private final BuildingMapper mapper;
    private final ExploitationRegionService regionService;
    private final AddressService addressService;

    @Override
    public ResponseShortBuildingDto save(NewBuildingDto buildingDto) {
        if (repository.existsByAddress(addressService.getAddressString(buildingDto.getAddressId()))) {
            throw new BadRequestException(
                    String.format("Building by address with id=%s is found", buildingDto.getAddressId())
            );
        }
        return mapper.mapToShortBuildingDto(repository.save(map(mapper.mapToBuilding(buildingDto)
                                                              , buildingDto.getBuildingType()
                                                              , buildingDto.getExploitationRegionId()
                                                              , buildingDto.getAddressId()))
        );
    }

    @Override
    public ResponseShortBuildingDto update(UpdateBuildingDto buildingDto) {
        if (repository.existsById(buildingDto.getId())) {
            return mapper.mapToShortBuildingDto( repository.save(map(mapper.mapToUpdateBuilding(buildingDto)
                                                                   , buildingDto.getBuildingType()
                                                                   , buildingDto.getExploitationRegionId()
                                                                   , buildingDto.getAddressId()))
            );
        }
        throw new NotFoundException(String.format("Building with id=%s not found for update.", buildingDto.getId()));
    }

    @Override
    public ResponseBuildingDto get(Long id) {
        return mapper.mapToFullBuildingDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Building with id=%s not found", id))));
    }

    @Override
    public List<ResponseShortBuildingDto> getAll(Long id) {
        if (id == null) {
            return repository.findAll()
                             .stream()
                             .map(mapper::mapToShortBuildingDto)
                             .toList();
        } else {
            return repository.findAllByExploitationRegionId(id)
                             .stream()
                             .map(mapper::mapToShortBuildingDto)
                             .toList();
        }
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Building with id=%s not found for delete.", id));
    }

    private Building map(Building building, String buildingType, Long exploitationRegionId, Long addressId) {
        return mapper.mapWitchData(building
                                , get(buildingType)
                                , addressService.getAddressString(addressId)
                                , regionService.getById(exploitationRegionId));
    }
}