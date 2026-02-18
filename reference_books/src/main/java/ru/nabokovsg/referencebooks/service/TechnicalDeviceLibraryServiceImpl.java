package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.NewTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.ResponseTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.UpdateTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.TechnicalDeviceLibraryMapper;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model.TechnicalDevice;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.referencebooks.repository.TechnicalDeviceLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalDeviceLibraryServiceImpl implements TechnicalDeviceLibraryService {

    private final TechnicalDeviceLibraryRepository repository;
    private final TechnicalDeviceLibraryMapper mapper;
    private final HeatSupplySiteLibraryService siteLibraryService;

    @Override
    public ResponseTechnicalDeviceLibraryDto save(NewTechnicalDeviceLibraryDto deviceDto) {
        exists(null, deviceDto.getFullName());
        return mapper.mapToResponseTechnicalDeviceLibraryDto(
                repository.save(mapper.mapToTechnicalDevice(deviceDto, siteLibraryService.getById(deviceDto.getSiteId())))
        );
    }

    @Override
    public ResponseTechnicalDeviceLibraryDto update(UpdateTechnicalDeviceLibraryDto deviceDto) {
        exists(deviceDto.getId(), deviceDto.getFullName());
        TechnicalDevice device = getById(deviceDto.getId());
        mapper.mapToUpdateTechnicalDevice(device, deviceDto);
        return mapper.mapToResponseTechnicalDeviceLibraryDto(repository.save(device));
    }

    @Override
    public ResponseTechnicalDeviceLibraryDto get(Long id) {
        return mapper.mapToResponseTechnicalDeviceLibraryDto(getById(id));
    }

    @Override
    public List<ResponseTechnicalDeviceLibraryDto> getAll(Long id, String name) {
        Set<TechnicalDevice> devices = repository.findAllBySiteId(id);
        if (name != null) {
            String deviceName = name.toLowerCase();
            devices = devices.stream()
                    .filter(v -> v.getFullName().toLowerCase().contains(deviceName)
                              || v.getShortName().toLowerCase().contains(deviceName))
                    .collect(Collectors.toSet());
        }
        return devices.stream()
                      .map(mapper::mapToResponseTechnicalDeviceLibraryDto)
                      .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.TECHNICAL_DEVICE.label);
    }

    private TechnicalDevice getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.TECHNICAL_DEVICE.label));
    }

    private void exists(Long id, String fullName) {
        boolean exists;
        if (id != null) {
            exists = !id.equals(repository.findIdByFullName(fullName).orElse(id));
        } else {
            exists = repository.existsByFullName(fullName);
        }
        if (exists) {
            throw new BadRequestException(
                    String.join("", BadRequestExceptionMassage.DUPLICATE.label,fullName));
        }
    }
}