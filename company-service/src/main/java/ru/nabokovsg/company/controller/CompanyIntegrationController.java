package ru.nabokovsg.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.company.dto.company.ResponseCompanyDto;
import ru.nabokovsg.company.dto.company.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.company.service.CompanyIntegrationService;

@RestController
@RequestMapping(
        value = "/company",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сведения о структурном подразделении",
        description="API для получения сведений о структурных подразделениях предприятия" +
                " для добавления записи в журнал выполненных работ")
public class CompanyIntegrationController {

    private final CompanyIntegrationService service;

    @Operation(summary = "Получить данные структурного подразделения по идентификаторам")
    @GetMapping("/journal")
    public ResponseEntity<ResponseCompanyDto> get(
            @RequestParam(name = "heatSupplyAreaId", required = false)
            @Parameter(description = "Идентификатор района теплоснабжения") Long heatSupplyAreaId
          , @RequestParam(name = "exploitationRegionId", required = false)
            @Parameter(description = "Идентификатор эксплуатационного участка") Long exploitationRegionId
          , @RequestParam(name = "addressId") @NotNull @Positive
            @Parameter(description = "Идентификатор адреса") Long addressId) {
        return ResponseEntity.ok().body(service.get(heatSupplyAreaId, exploitationRegionId, addressId));
    }

    @Operation(summary = "Получить данные для шаблона заголовка документа по идентификатору филиала")
    @GetMapping("/branch/{id}")
    public ResponseEntity<ResponseDocumentHeaderTemplateDto> getByBranch(
            @PathVariable(name = "id", required = false)
            @Parameter(description = "Идентификатор филиала") Long branchId) {
        return ResponseEntity.ok().body(service.getByBranch(branchId));
    }

    @Operation(summary = "Получить данные для шаблона заголовка документа по идентификатору подразделения")
    @GetMapping("/department/{id}")
    public ResponseEntity<ResponseDocumentHeaderTemplateDto> getByDepartment(
            @PathVariable(name = "id", required = false)
            @Parameter(description = "Идентификатор подразделения") Long departmentId) {
        return ResponseEntity.ok().body(service.getByDepartment(departmentId));
    }

    @Operation(summary = "Получить данные для шаблона заголовка документа по идентификатору района теплоснабжения")
    @GetMapping("/area/{id}")
    public ResponseEntity<ResponseDocumentHeaderTemplateDto> getByHeatSupplyArea(
            @PathVariable(name = "id", required = false)
            @Parameter(description = "Идентификатор района теплоснабжения") Long heatSupplyAreaId) {
        return ResponseEntity.ok().body(service.getByHeatSupplyArea(heatSupplyAreaId));
    }

    @Operation(summary = "Получить данные для шаблона заголовка документа по идентификатору эксплуатационного участка")
    @GetMapping("/region/{id}")
    public ResponseEntity<ResponseDocumentHeaderTemplateDto> getByExploitationRegion(
            @PathVariable(name = "id", required = false)
            @Parameter(description = "Идентификатор эксплуатационного участка") Long exploitationRegionId) {
        return ResponseEntity.ok().body(service.getByExploitationRegion(exploitationRegionId));
    }
}