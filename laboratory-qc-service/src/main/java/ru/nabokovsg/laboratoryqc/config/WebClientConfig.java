package ru.nabokovsg.laboratoryqc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webCompany() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/WorkVisionWeb")
                .build();
    }

    @Bean
    public WebClient webEquipment() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083/WorkVisionWeb")
                .build();
    }
}
