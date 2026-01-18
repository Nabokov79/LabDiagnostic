package ru.nabokovsg.referencebooks.service_factory;

public interface ElementNameFactory {

    String create(Long elementLibraryId, Long partElementLibraryId);

    String createDimensions(Integer diameter, Integer length, Integer height, Integer width);

    String createStandardSize(Integer diameterSize, Double thicknessSize);
}