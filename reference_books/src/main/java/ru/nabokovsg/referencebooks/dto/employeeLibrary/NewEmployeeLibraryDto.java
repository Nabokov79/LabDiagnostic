package ru.nabokovsg.referencebooks.dto.employeeLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового сотрудника предприятия")
public class NewEmployeeLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "branch id should not be null")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Идентификатор")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Идентификатор")
    @Positive(message = "source id can only be positive")
    private Long sourceId;
    @Schema(description = "Имя")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Отчество")
    @NotBlank(message = "patronymic should not be blank")
    private String patronymic;
    @Schema(description = "Фамилия")
    @NotBlank(message = "surname should not be blank")
    private String surname;
    @Schema(description = "Должность")
    @NotBlank(message = "post should not be blank")
    private String post;
    @Schema(description = "электронная почта")
    @NotBlank(message = "email should not be blank")
    @Email(message = "email invalid")
    private String email;
}