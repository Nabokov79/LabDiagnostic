package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.NewHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseShortHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.UpdateHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.HeatSupplySourceLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;
import ru.nabokovsg.referencebooks.repository.HeatSupplySourceLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeatSupplySourceLibraryServiceImpl implements HeatSupplySourceLibraryService {

    private final HeatSupplySourceLibraryRepository repository;
    private final HeatSupplySourceLibraryMapper mapper;
    private final DepartmentLibraryService departmentService;
    private final static String NOT_FOUND = "Источник теплоснабжения не обнаружен.";

    @Override
    public ResponseShortHeatSupplySourceLibraryDto save(NewHeatSupplySourceLibraryDto sourceDto) {
        exists(null, sourceDto.getAddress());
        return mapper.mapToResponseShortHeatSupplySourceLibraryDto(
                repository.save(mapper.mapToHeatSupplySource(sourceDto
                                                           , departmentService.getById(sourceDto.getDepartmentId()))));
    }

    @Override
    public ResponseShortHeatSupplySourceLibraryDto update(UpdateHeatSupplySourceLibraryDto sourceDto) {
        exists(sourceDto.getId(), sourceDto.getAddress());
        HeatSupplySourceLibrary heatSupplySource = getById(sourceDto.getId());
        mapper.mapToUpdateHeatSupplySource(heatSupplySource, sourceDto);
        return mapper.mapToResponseShortHeatSupplySourceLibraryDto(repository.save(heatSupplySource));
    }

    @Override
    public ResponseHeatSupplySourceLibraryDto get(Long id) {
        return mapper.mapToResponseHeatSupplySourceDto(getById(id));
    }

    @Override
    public List<ResponseShortHeatSupplySourceLibraryDto> getAll(Long id, String source) {
        Set<HeatSupplySourceLibrary> sources = repository.findAllByDepartmentIdOrderBySource(id);
        if (source != null) {
            String sourceName = source.toLowerCase();
            sources = sources.stream()
                             .filter(v -> v.getSource().toLowerCase().contains(sourceName)
                                       || v.getAddress().toLowerCase().contains(sourceName))
                             .collect(Collectors.toSet());
        }
        return sources.stream()
                      .map(mapper::mapToResponseShortHeatSupplySourceLibraryDto)
                      .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NOT_FOUND);
    }

    @Override
    public HeatSupplySourceLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    private void exists(Long id, String address) {
        boolean exists;
        if (id != null) {
            exists = !id.equals(repository.findIdByAddress(address).orElse(id));

        } else {
            exists = repository.existsByAddress(address);
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, address));
        }
    }
}