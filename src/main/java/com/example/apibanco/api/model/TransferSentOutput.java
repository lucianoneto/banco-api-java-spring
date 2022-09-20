package com.example.apibanco.api.model;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TransferSentOutput {

    private Float value;

    private Long idDestinyAccount;

    private LocalTime time;

    private LocalDate date;


}
