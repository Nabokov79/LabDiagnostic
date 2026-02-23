package ru.nabokovsg.equipmentunit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipmentunit.dto.client.*;
import ru.nabokovsg.equipmentunit.service.StructureOrganizationService;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Структура предприятия",
        description="API для обмена данными структуры предприятия с сервисом справочником")
public class StructureOrganizationController {

    private final StructureOrganizationService service;

    @Operation(summary = "Сохранить/изменить данные филиала")
    @PostMapping("/branch")
    public ResponseEntity<HttpStatus> saveBranch(@RequestBody @Parameter(description = "Филиал")
                                                                     BranchLibraryDto branchDto) {
        service.saveBranch(branchDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Сохранить/изменить данные подразделения")
    @PostMapping("/department")
    public ResponseEntity<HttpStatus> saveDepartment(@RequestBody @Parameter(description = "Подразделение")
                                                                        DepartmentLibraryDto departmentDto) {
        service.saveDepartment(departmentDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Сохранить/изменить данные источника теплоснабжения")
    @PostMapping("/source")
    public ResponseEntity<HttpStatus> saveSource(@RequestBody @Parameter(description = "Источник теплоснабжения")
                                                                            HeatSupplySourceLibraryDto sourceDto) {
        service.saveSource(sourceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Сохранить/изменить данные участка теплоснабжения")
    @PostMapping("/site")
    public ResponseEntity<HttpStatus> saveSite(@RequestBody @Parameter(description = "Участок тепловой сети")
                                                                            HeatSupplySiteLibraryDto siteDto) {
        service.saveSite(siteDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Сохранить/изменить данные технического устройства")
    @PostMapping("/device")
    public ResponseEntity<HttpStatus> saveDevice(@RequestBody @Parameter(description = "Техническое устройство")
                                                                            TechnicalDeviceLibraryDto deviceDto) {
        service.saveDevice(deviceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}