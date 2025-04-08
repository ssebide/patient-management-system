package com.pm.patient_service.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PatientVisitEvent {

    private String patientId;

    private LocalDateTime dateTime;
}
