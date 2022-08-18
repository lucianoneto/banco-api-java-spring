package com.example.apibanco.api.exception;


import lombok.Getter;

import java.util.HashMap;

public class NegocioException extends RuntimeException {
    @Getter
    private final HashMap<String, String> camposInvalidos;
    @Getter
    private final String titulo;

    public NegocioException(String titulo, HashMap<String, String> camposInvalidos) {
        this.titulo = titulo;
        this.camposInvalidos = camposInvalidos;
    }
}
