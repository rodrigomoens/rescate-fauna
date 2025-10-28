package com.uade.tpo.rescate_fauna.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.math3.analysis.function.Add;

import lombok.Data;

@Data
@Document(collection = "pacientes")
public class Patient {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private Integer DNI;
    private String obraSocial;
    private ArrayList<Habit> habitos;
    private ArrayList<MedicalHistory> historialClinico;
}

