package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.address.NewAddressDto;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.address.UpdateAddressDto;

import java.util.List;

public interface AddressService {

    ResponseAddressDto save(NewAddressDto addressDto);

    ResponseAddressDto update(UpdateAddressDto addressDto);

    String getAddressString(Long id);

    List<ResponseAddressDto> getAll(String street);

    void delete(Long id);
}