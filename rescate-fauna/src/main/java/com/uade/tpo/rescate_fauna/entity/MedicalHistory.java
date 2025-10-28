package com.uade.tpo.rescate_fauna.entity;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MedicalHistory {
    private String id;
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
}
