package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.NewTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.ResponseTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.UpdateTechnicalDeviceLibraryDto;

import java.util.List;

public interface TechnicalDeviceLibraryService {

    ResponseTechnicalDeviceLibraryDto save(NewTechnicalDeviceLibraryDto deviceDto);

    ResponseTechnicalDeviceLibraryDto update( UpdateTechnicalDeviceLibraryDto deviceDto);

    ResponseTechnicalDeviceLibraryDto get(Long id);

   List<ResponseTechnicalDeviceLibraryDto> getAll(Long id, String name);

   void delete(Long id);
}