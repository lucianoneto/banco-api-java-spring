package com.example.apibanco.domain.utils;

import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
public class Utils {
    public static java.sql.Date dateNow() {
        return Date.valueOf(LocalDate.now());
    }

    public static Time timeNow() {
        return Time.valueOf(LocalTime.now());
    }


}
