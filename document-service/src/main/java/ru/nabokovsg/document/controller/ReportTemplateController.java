package ru.nabokovsg.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.document.service.PageTitleTemplateService;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/document/template/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета", description="API для работы с данными шаблона отчета")
public class ReportTemplateController {

    private final PageTitleTemplateService service;
}