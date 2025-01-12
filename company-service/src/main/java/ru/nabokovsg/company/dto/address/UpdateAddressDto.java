package ru.nabokovsg.company.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об адресе")
public class UpdateAddressDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Почтовый индекс")
    private Integer index;
    @Schema(description = "Населенный пункт")
    @NotBlank(message = "city should not be blank")
    private String city;
    @Schema(description = "Название улицы")
    @NotBlank(message = "street name should not be blank")
    private String street;
    @Schema(description = "Номер дома")
    @NotBlank(message = "house number should not be blank")
    private String houseNumber;
    @Schema(description = "Номер корпуса дома")
    private String buildingNumber;
    @Schema(description = "Литера дома")
    private String letter;
}