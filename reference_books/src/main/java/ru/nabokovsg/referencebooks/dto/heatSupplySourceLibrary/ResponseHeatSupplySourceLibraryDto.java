package ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseShortHeatSupplySiteLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные источника теплоснабжения")
public class ResponseHeatSupplySourceLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип источника")
    private String source;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Участки тепловой сети")
    private List<ResponseShortHeatSupplySiteLibraryDto> heatSupplySites;
}