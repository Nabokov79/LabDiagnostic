package ru.nabokovsg.measurement.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;

@Component
public class EquipmentClient {

    private final WebClient client;

    @Autowired
    public EquipmentClient(@Qualifier(value = "webEquipment") WebClient client) {
        this.client = client;
    }

    public EquipmentDto getEquipment(String path, MultiValueMap<String, String> params) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(params)
                        .build())
                .retrieve()
                .bodyToMono(EquipmentDto.class)
                .block();
    }

    public EquipmentDto getEquipment(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(EquipmentDto.class)
                .block();
    }
}