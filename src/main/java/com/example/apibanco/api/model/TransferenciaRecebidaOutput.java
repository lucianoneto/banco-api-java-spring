package com.example.apibanco.api.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class TransferenciaRecebidaOutput {

    private Float valor;

    private Long idContaOrigem;

    private Time horario;

    private Date data;

}
