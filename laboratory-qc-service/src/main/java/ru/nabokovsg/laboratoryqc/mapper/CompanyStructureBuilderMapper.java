package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.CompanyStructureDto;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

@Mapper(componentModel = "spring")
public interface CompanyStructureBuilderMapper {

    @Mapping(source = "structure.branchFullName", target = "branch")
    @Mapping(source = "structure.heatSupplyArea.fullName", target = "heatSupplyArea")
    @Mapping(source = "structure.exploitationRegion.fullName", target = "exploitationRegion")
    @Mapping(source = "address", target = "address")
    void mapToCompanyStructure(@MappingTarget JournalCompletedWork journal
                                            , CompanyStructureDto structure
                                            , String address);

}