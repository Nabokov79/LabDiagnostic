package ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.ResponseTechnicalDeviceLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные участка тепловой сети")
public class ResponseHeatSupplySiteLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное описание участка тепловой сети")
    private String fullDescription;
    @Schema(description = "Краткое описание участка тепловой сети")
    private String shortDescription;
    @Schema(description = "Технические устройства")
    private List<ResponseTechnicalDeviceLibraryDto> devices;
}