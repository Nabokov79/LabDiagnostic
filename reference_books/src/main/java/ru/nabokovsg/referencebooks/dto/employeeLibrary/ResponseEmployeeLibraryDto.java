package ru.nabokovsg.referencebooks.dto.employeeLibrary;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные сотрудника предприятия")
public class ResponseEmployeeLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Филиал")
    private String branch;
    @Schema(description = "Подразделение")
    private String department;
    @Schema(description = "ФИО сотрудника")
    private String initials;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "электронная почта")
    private String email;
}