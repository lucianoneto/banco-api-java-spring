package com.example.apibanco.api.model;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class TransferSentOutput {

    private Float value;

    private Long idDestinyAccount;

    private Time time;

    private Date date;


}
