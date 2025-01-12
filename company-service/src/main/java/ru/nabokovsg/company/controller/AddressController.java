package ru.nabokovsg.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.company.dto.address.NewAddressDto;
import ru.nabokovsg.company.dto.address.ResponseAddressDto;
import ru.nabokovsg.company.dto.address.UpdateAddressDto;
import ru.nabokovsg.company.service.AddressService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/address",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Адрес",
        description = "API для работы с данными адреса")
public class AddressController {

    private final AddressService service;

    @Operation(summary = "Добавление нового адреса")
    @PostMapping
    public ResponseEntity<ResponseAddressDto> save(@RequestBody @Valid
                                                   @Parameter(description = "Адрес") NewAddressDto addressDto) {
        return ResponseEntity.ok().body(service.save(addressDto));
    }

    @Operation(summary = "Изменение данных адреса")
    @PatchMapping
    public ResponseEntity<ResponseAddressDto> update(@RequestBody @Valid
                                                     @Parameter(description = "Адрес") UpdateAddressDto addressDto) {
        return ResponseEntity.ok().body(service.update(addressDto));
    }

    @Operation(summary = "Получение всех адресов")
    @GetMapping
    public ResponseEntity<List<ResponseAddressDto>> getAll(@RequestParam(required = false)
                                                       @Parameter(description = "Наименование улицы") String street) {
        return ResponseEntity.ok().body(service.getAll(street));
    }

    @Operation(summary = "Удаление адреса")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok( "Адрес успешно удален.");
    }
}