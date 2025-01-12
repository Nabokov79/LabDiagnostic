package ru.nabokovsg.equipment.dto.equipmentPassport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные паспорта оборудования")
public class ResponseEquipmentPassportDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    private String header;
    @Schema(description = "Значение")
    private String meaning;
    @Schema(description = "Использовать в протоколе")
    private Boolean useToProtocol;
}