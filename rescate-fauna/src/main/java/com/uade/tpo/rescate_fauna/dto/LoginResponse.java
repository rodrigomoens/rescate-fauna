package com.uade.tpo.rescate_fauna.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;
}
