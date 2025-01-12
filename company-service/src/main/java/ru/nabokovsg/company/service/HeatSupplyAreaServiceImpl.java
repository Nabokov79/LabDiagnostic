package ru.nabokovsg.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.heatSupplyArea.NewHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseShortHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.UpdateHeatSupplyAreaDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mapper.HeatSupplyAreaMapper;
import ru.nabokovsg.company.model.HeatSupplyArea;
import ru.nabokovsg.company.repository.HeatSupplyAreaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeatSupplyAreaServiceImpl implements HeatSupplyAreaService {

    private final HeatSupplyAreaRepository repository;
    private final HeatSupplyAreaMapper mapper;
    private final BranchService branchService;

    @Override
    public ResponseShortHeatSupplyAreaDto save(NewHeatSupplyAreaDto areaDto) {
        if (repository.existsByFullName(areaDto.getFullName())) {
            throw new BadRequestException(
                    String.format("HeatSupplyArea with full name=%s is found", areaDto.getFullName())
            );
        }
        return mapper.mapToShortHeatSupplyAreaDto(
                repository.save(map(mapper.mapToHeatSupplyArea(areaDto), areaDto.getBranchId()))
        );
    }

    @Override
    public ResponseShortHeatSupplyAreaDto update(UpdateHeatSupplyAreaDto areaDto) {
        if (repository.existsById(areaDto.getId())) {
            return mapper.mapToShortHeatSupplyAreaDto(
                    repository.save(map(mapper.mapToUpdateHeatSupplyArea(areaDto), areaDto.getBranchId()))
            );
        }
        throw new NotFoundException(
                String.format("HeatSupplyArea with name=%s not found for update.", areaDto.getFullName()));
    }

    @Override
    public ResponseHeatSupplyAreaDto get(Long id) {
        return mapper.mapToFullHeatSupplyAreaDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("HeatSupplyArea with id=%s not found.", id))));
    }

    @Override
    public List<ResponseShortHeatSupplyAreaDto> getAll(Long branchId, String name) {
        Set<HeatSupplyArea> heatSupplyAreas;
        if (branchId != null && branchId > 0) {
            heatSupplyAreas = repository.findAllByBranchId(branchId);
        } else {
            heatSupplyAreas = new HashSet<>(repository.findAll());
        }
        if (name != null) {
            String fullName = name.toLowerCase();
            heatSupplyAreas = heatSupplyAreas.stream()
                                             .filter(v -> v.getFullName().toLowerCase().contains(fullName))
                                             .collect(Collectors.toSet());
        }
        return heatSupplyAreas.stream()
                              .map(mapper::mapToShortHeatSupplyAreaDto)
                              .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("HeatSupplyArea with id=%s not found for delete.", id));
    }

    private HeatSupplyArea map(HeatSupplyArea heatSupplyArea, Long branchId) {
        return mapper.mapWithBranch(heatSupplyArea, branchService.getById(branchId));
    }
}