package ru.nabokovsg.company.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового адреса")
public class NewAddressDto {

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