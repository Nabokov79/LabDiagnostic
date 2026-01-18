package ru.nabokovsg.referencebooks.dto.employeeLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Краткие данные сотрудника предприятия")
public class ResponseShortEmployeeLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Филиал")
    private String branch;
    @Schema(description = "Подразделение")
    private String department;
    @Schema(description = "Источник теплоснабжения")
    private String source;
    @Schema(description = "Фимилия,имя, отчество полностью")
    private String fullName;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "электронная почта")
    private String email;
}