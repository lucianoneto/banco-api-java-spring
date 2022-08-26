package com.example.apibanco.api.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class TransferReceivedOutput {

    private Float value;

    private Long idOriginAccount;

    private Time time;

    private Date date;

}
