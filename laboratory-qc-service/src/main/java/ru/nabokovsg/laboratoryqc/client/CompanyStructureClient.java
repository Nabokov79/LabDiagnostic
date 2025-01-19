package ru.nabokovsg.laboratoryqc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.CompanyStructureDto;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.DepartmentStructureDto;

@Component
public class CompanyStructureClient {

    private final WebClient client;

    @Autowired
    public CompanyStructureClient(@Qualifier(value = "webCompany") WebClient client) {
        this.client = client;
    }

    public CompanyStructureDto get(String patch, MultiValueMap<String, String> params) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder.path(patch)
                                                  .queryParams(params)
                                                  .build())
                    .retrieve()
                    .bodyToMono(CompanyStructureDto.class)
                    .block();
    }

    public DepartmentStructureDto get(String patch) {
        return client.get()
                .uri(patch)
                .retrieve()
                .bodyToMono(DepartmentStructureDto.class)
                .block();
    }
}