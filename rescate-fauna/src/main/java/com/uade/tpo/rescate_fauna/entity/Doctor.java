package com.uade.tpo.rescate_fauna.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.math3.analysis.function.Add;

import lombok.Data;

@Data
@Document(collection = "doctores")
public class Doctor {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private Integer matricula;
    private String especialidad;
}
