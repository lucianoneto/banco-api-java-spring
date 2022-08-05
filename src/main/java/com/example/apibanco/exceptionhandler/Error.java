package com.example.apibanco.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Error {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    private List<String> camposString;
    public Error(Integer status, String titulo, List<String> camposString) {
        this.status = status;
        this.titulo = titulo;
        this.camposString = camposString;
    }

    @Getter
    @AllArgsConstructor
    public static class Campo{
        private String nome;
        private String mensagem;
    }
}
