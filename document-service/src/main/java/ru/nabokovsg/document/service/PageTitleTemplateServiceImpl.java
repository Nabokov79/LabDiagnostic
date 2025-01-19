package ru.nabokovsg.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.document.mapper.PageTitleTemplateMapper;
import ru.nabokovsg.document.repository.HeaderTemplateRepository;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final HeaderTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
}
