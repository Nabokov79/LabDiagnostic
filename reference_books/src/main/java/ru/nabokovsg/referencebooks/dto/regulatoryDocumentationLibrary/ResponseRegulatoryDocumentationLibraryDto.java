package ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseShortEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryStatus;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нормативно-технической документации")
public class ResponseRegulatoryDocumentationLibraryDto {

    @Schema(description = "Идентификатор")
    private long id;
    @Schema(description = "Полное наименование документа")
    private String fullName;
    @Schema(description = "Тип и номер документа")
    private String document;
    @Schema(description = "Наименование документа")
    private String documentName;
    @Schema(description = "Тип документа")
    private RegulatoryDocumentationLibraryType type;
    @Schema(description = "Статус документа")
    private RegulatoryDocumentationLibraryStatus status;
    @Schema(description = "Область распространения документа")
    private String areaDistribution;
    @Schema(description = "Типы оборудования")
    private List<ResponseShortEquipmentLibraryDto> equipments;
}
