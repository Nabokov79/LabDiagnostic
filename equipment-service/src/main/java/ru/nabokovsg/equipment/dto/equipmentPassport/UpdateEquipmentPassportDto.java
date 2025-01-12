package ru.nabokovsg.equipment.dto.equipmentPassport;

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
@Schema(description = "Данные для добавления/изменения информации паспорта оборудования")
public class UpdateEquipmentPassportDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequentialNumber should not be null")
    @Positive(message = "sequentialNumber can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    @NotBlank(message = "header should not be blank")
    private String header;
    @Schema(description = "Значение")
    @NotBlank(message = "meaning should not be blank")
    private String meaning;
    @Schema(description = "Указать в протоколе")
    @NotNull(message = "useToProtocol should not be null")
    private Boolean useToProtocol;
}