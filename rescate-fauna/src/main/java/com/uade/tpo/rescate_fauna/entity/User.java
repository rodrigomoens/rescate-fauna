package com.uade.tpo.rescate_fauna.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.math3.analysis.function.Add;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String rol;

}
