package ru.nabokovsg.referencebooks.dto.equipmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseShortRegulatoryDocumentationLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида оборудования")
public class ResponseEquipmentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Габаритные размеры")
    private String dimensions;
    @Schema(description = "Период стабилизации основания")
    private Integer periodStabilization;
    @Schema(description = "Элементы")
    private List<ResponseShortElementLibraryDto> elements;
    @Schema(description = "Нормативно-техническая документация")
    private List<ResponseShortRegulatoryDocumentationLibraryDto> documentations;
}