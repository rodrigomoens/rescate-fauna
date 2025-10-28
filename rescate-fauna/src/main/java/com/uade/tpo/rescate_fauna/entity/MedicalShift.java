package com.uade.tpo.rescate_fauna.entity;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.math3.analysis.function.Add;

import lombok.Data;

@Data
@Document(collection = "turnos_medicos")
public class MedicalShift {
    @Id
    private String id;
    private Integer idPatient;
    private Integer idDoctor;
    private LocalDate date;
}
