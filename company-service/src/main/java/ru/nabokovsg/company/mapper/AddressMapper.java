package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.company.dto.address.NewAddressDto;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.address.UpdateAddressDto;
import ru.nabokovsg.company.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address mapToAddress(NewAddressDto addressDto);

    Address mapToUpdateAddress(UpdateAddressDto addressDto);

    ResponseAddressDto mapToResponseAddressDto(Address address);
}