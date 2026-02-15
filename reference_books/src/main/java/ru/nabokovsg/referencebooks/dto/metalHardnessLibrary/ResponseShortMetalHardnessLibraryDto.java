package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные твердости металла элементов оборудования")
public class ResponseShortMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Нормативно-технический документ")
    private String documentation;
    @Schema(description = "Полное наименование типа оборудования")
    private String equipmentFullName;
    @Schema(description = "Полное наименование элемента (элемент + подэлемент)")
    private String elementFullName;
    @Schema(description = "Типоразмер элемента(подэлемента)")
    private String standardSize;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}