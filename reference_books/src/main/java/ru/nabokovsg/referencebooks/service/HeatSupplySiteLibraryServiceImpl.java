package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.NewHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseShortHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.UpdateHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.HeatSupplySiteLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.HeatSupplySiteLibrary;
import ru.nabokovsg.referencebooks.repository.HeatSupplySiteLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeatSupplySiteLibraryServiceImpl implements HeatSupplySiteLibraryService {

    private final HeatSupplySiteLibraryRepository repository;
    private final HeatSupplySiteLibraryMapper mapper;
    private final HeatSupplySourceLibraryService sourceLibraryService;
    private final static String NOT_FOUND = "Участок тепловой сети не обнаружен.";

    @Override
    public ResponseShortHeatSupplySiteLibraryDto save(NewHeatSupplySiteLibraryDto siteDto) {
        exists(null, siteDto.getFullDescription());
        return mapper.mapToResponseShortHeatSupplySiteDto(
                repository.save(mapper.mapToHeatSupplySite(siteDto
                                                         , sourceLibraryService.getById(siteDto.getSourceId()))));
    }

    @Override
    public ResponseShortHeatSupplySiteLibraryDto update(UpdateHeatSupplySiteLibraryDto regionDto) {
        exists(regionDto.getId(), regionDto.getFullDescription());
        HeatSupplySiteLibrary heatSupplySite = getById(regionDto.getId());
        mapper.mapToUpdateHeatSupplySite(heatSupplySite, regionDto);
        return mapper.mapToResponseShortHeatSupplySiteDto(repository.save(heatSupplySite));
    }

    @Override
    public ResponseHeatSupplySiteLibraryDto get(Long id) {
        return mapper.mapToResponseHeatSupplySiteDto(getById(id));
    }

    @Override
    public List<ResponseShortHeatSupplySiteLibraryDto> getAll(Long id, String name) {
        Set<HeatSupplySiteLibrary> sites = repository.findAllBySourceId(id);
        if (name != null) {
            String siteName = name.toLowerCase();
            sites = sites.stream()
                    .filter(v -> v.getFullDescription().toLowerCase().contains(siteName)
                            || v.getShortDescription().toLowerCase().contains(siteName))
                    .collect(Collectors.toSet());
        }
        return sites.stream()
                    .map(mapper::mapToResponseShortHeatSupplySiteDto)
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
    public HeatSupplySiteLibrary getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    private void exists(Long id, String fullDescription) {
        boolean exists;
        if (id != null) {
            exists = !id.equals(repository.findIdByFullDescription(fullDescription).orElse(id));
        } else {
            exists = repository.existsByFullDescription(fullDescription);
        }
        if (exists) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, fullDescription));
        }
    }
}