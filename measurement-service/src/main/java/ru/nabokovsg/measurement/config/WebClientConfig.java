package ru.nabokovsg.measurement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value(value = "${services.equipment}")
    private String equipmentUrl;

    @Bean
    public WebClient webEquipment() {
        return WebClient.builder()
                .baseUrl(equipmentUrl)
                .build();
    }
}