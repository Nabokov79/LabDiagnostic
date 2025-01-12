package ru.nabokovsg.company.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные адреса")
public class ResponseAddressDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Почтовый индекс")
    private Integer index;
    @Schema(description = "Населенный пункт")
    private String city;
    @Schema(description = "Название улицы")
    private String street;
    @Schema(description = "Номер дома")
    private String houseNumber;
    @Schema(description = "Номер корпуса дома")
    private Integer buildingNumber;
    @Schema(description = "Литера дома")
    private String letter;
}