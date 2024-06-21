package com.prueba.tecnica.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Studiantes {
    private String nombre;
    private String sede;
    private String correo;
    private String[] asignaturas;
    private boolean enviado;
}

