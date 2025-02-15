package ru.nabokovsg.measurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MeasurementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeasurementApplication.class, args);
    }
}