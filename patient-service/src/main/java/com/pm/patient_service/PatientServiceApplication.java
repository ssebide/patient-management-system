package com.pm.patient_service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.patient_service.config.KafkaConfigProps;
import com.pm.patient_service.domain.PatientVisitEvent;

@SpringBootApplication
public class PatientServiceApplication {

    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate, final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
        final PatientVisitEvent event = PatientVisitEvent.builder()
                .patientId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .build();

        final String payload = objectMapper.writeValueAsString(event);

        return args -> {
            kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        };

    }

}
