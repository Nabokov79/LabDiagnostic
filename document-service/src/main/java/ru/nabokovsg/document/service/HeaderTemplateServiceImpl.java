package ru.nabokovsg.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.document.mapper.HeaderTemplateMapper;
import ru.nabokovsg.document.repository.HeaderTemplateRepository;

@Service
@RequiredArgsConstructor
public class HeaderTemplateServiceImpl implements HeaderTemplateService {

    private final HeaderTemplateRepository repository;
    private final HeaderTemplateMapper mapper;
}
