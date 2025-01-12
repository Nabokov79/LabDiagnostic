package ru.nabokovsg.laboratoryqc.dto.measuringTool;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о измерительном инструменте(приборе)")
public class UpdateMeasuringToolDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id measuring tool should not be blank")
    @Positive(message = "id measuring tool must be positive")
    private Long id;
    @Schema(description = "Наименование")
    @NotBlank(message = "toll name should not be blank")
    private String tollName;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Заводской номер")
    @NotBlank(message = "work number should not be blank")
    private String workNumber;
    @Schema(description = "Назначение")
    @NotBlank(message = "purpose should not be blank")
    private String purpose;
    @Schema(description = "Дата изготовления")
    @NotNull(message = "manufacturing should not be blank")
    private LocalDate manufacturing;
    @Schema(description = "Дата начала эксплуатации")
    @NotNull(message = "exploitation should not be blank")
    private LocalDate exploitation;
    @Schema(description = "Организация-изготовитель")
    @NotBlank(message = "manufacturer should not be blank")
    private String manufacturer;
    @Schema(description = "Диапазон измерений")
    private String  measuringRange;
    @Schema(description = "Характеристики")
    private String characteristics;
    @Schema(description = "Владелец инструмента(прибора) измерения")
    @NotBlank(message = "owner should not be blank")
    private String owner;
    @Schema(description = "Дата поверки")
    @NotNull(message = "verification date should not be blank")
    private LocalDate verificationDate;
    @Schema(description = "Дата следующей поверки")
    @NotNull(message = "next verification date should not be blank")
    private LocalDate nextVerificationDate;
    @Schema(description = "Метрологическая организация")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Номер свидетельства о поверке")
    @NotBlank(message = "certificate number should not be blank")
    private String certificateNumber;
    @Schema(description = "Номер госреестра")
    @NotBlank(message = "registry should not be blank")
    private String registry;
    @Schema(description = "Примечание")
    private String note;
    @Schema(description = "Вид контроля")
    private String controlType;
    @Schema(description = "Идентификатор сотрудника")
    private Long employeeId;
}