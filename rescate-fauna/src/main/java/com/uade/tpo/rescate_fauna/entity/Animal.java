package com.uade.tpo.rescate_fauna.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.math3.analysis.function.Add;

import lombok.Data;

@Data
@Document(collection = "animales")
public class Animal {
    @Id
    private String id;
    private String nombreComun;
    private String nombreCientifico;
    private String categoriaConservacion;
    private Integer edadAproximada;
    private String genero;
    private String identificadorChip;
    private ArrayList<HistorialMedico> historialMedico;
    @Data
    public static class HistorialMedico{
        private LocalDate fecha;
        private String diagnostico;
        private String tratamiento;
    }
}
