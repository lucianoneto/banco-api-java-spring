package com.example.apibanco.api.model;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class TransferenciaEnviadaOutput {


    private Float valor;

    private Long idContaDestino;

    private Time horario;

    private Date data;


}
