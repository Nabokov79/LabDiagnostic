package ru.nabokovsg.company.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.address.NewAddressDto;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.address.UpdateAddressDto;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mapper.AddressMapper;
import ru.nabokovsg.company.model.Address;
import ru.nabokovsg.company.model.QAddress;
import ru.nabokovsg.company.repository.AddressRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseAddressDto save(NewAddressDto addressDto) {
        return mapper.mapToResponseAddressDto(
                Objects.requireNonNullElseGet(getByPredicate(addressDto)
                                            , () -> repository.save(mapper.mapToAddress(addressDto)))
        );
    }

    @Override
    public ResponseAddressDto update(UpdateAddressDto addressDto) {
        if (repository.existsById(addressDto.getId())) {
            return mapper.mapToResponseAddressDto(repository.save(mapper.mapToUpdateAddress(addressDto)));
        }
        throw new NotFoundException(String.format("Address with id=%s not found for update.", addressDto.getId()));
    }

    private Address get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Address with id=%s not found", id)));
    }

    @Override
    public String getAddressString(Long id) {
        return toString(get(id));
    }

    private String toString(Address address) {
        String delimiter = ", ";
        String string = String.join(delimiter, address.getCity()
                , String.join(delimiter, address.getStreet()
                        , String.join("",  "д.", String.valueOf(address.getHouseNumber()))));
        if (address.getBuildingNumber() != null) {
            string = String.join(delimiter, string, String.join(""
                    , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(delimiter, string, String.join("", "лит.", address.getLetter()));
        }
        if (address.getIndex() != null) {
            return String.join(delimiter, String.valueOf(address.getIndex()), string);
        } else {
            return string;
        }
    }

    @Override
    public List<ResponseAddressDto> getAll(String street) {
        List<Address> addresses = repository.findAll();
        if (street != null) {
            String name = street.toLowerCase();
            return repository.findAll()
                    .stream()
                    .filter(v -> v.getStreet().toLowerCase().contains(name))
                            .map(mapper::mapToResponseAddressDto)
                            .toList();
        }
        return addresses.stream()
                        .map(mapper::mapToResponseAddressDto)
                        .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Address with id=%s not found for delete.", id));
    }

    private Address getByPredicate(NewAddressDto addressDto) {
        QAddress address = QAddress.address;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (addressDto.getIndex() != null) {
            booleanBuilder.and(address.index.eq(addressDto.getIndex()));
        }
        if (addressDto.getCity() != null) {
            booleanBuilder.and(address.city.eq(addressDto.getCity()));
        }
        if (addressDto.getStreet() != null) {
            booleanBuilder.and(address.street.eq(addressDto.getStreet()));
        }
        if (addressDto.getHouseNumber() != null) {
            booleanBuilder.and(address.houseNumber.eq(addressDto.getHouseNumber()));
        }
        if (addressDto.getBuildingNumber() != null) {
            booleanBuilder.and(address.buildingNumber.eq(addressDto.getBuildingNumber()));
        }
        if (addressDto.getLetter() != null) {
            booleanBuilder.and(address.letter.eq(addressDto.getLetter()));
        }
        return new JPAQueryFactory(em).from(address)
                                      .select(address)
                                      .where(booleanBuilder)
                                      .fetchOne();
    }
}