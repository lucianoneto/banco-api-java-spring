package com.example.apibanco.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;


@Getter
@Setter
@Builder
public class Error {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private HashMap<String, String> camposInvalidos;

}