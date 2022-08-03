package com.example.apibanco.utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String dateTimeNow(LocalDateTime dataHoraAtual) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHoraAtual.format(formatter);
    }

    public static java.sql.Date dateNow() {
        return Date.valueOf(LocalDate.now());
    }

    public static Time timeNow() {
        return Time.valueOf(LocalTime.now());
    }
}
