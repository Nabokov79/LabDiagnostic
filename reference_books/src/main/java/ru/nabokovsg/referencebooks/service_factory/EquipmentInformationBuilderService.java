package ru.nabokovsg.referencebooks.service_factory;


import java.util.Map;

public interface EquipmentInformationBuilderService {

    Map<String, String> getByElement(Long elementLibraryId);

    Map<String, String> getByPartElement(Long partElementLibraryId);
}