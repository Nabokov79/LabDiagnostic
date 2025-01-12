package ru.nabokovsg.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.document.service.HeaderTemplateService;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/document/template/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Работа с документами", description="API для работы с данными документов и шаблонов документов")
public class HeaderTemplateController {

    private final HeaderTemplateService service;
}