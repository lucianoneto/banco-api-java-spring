package com.example.apibanco.utils;

import com.example.apibanco.repository.transactions.DepositoRepository;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
public class Utils {
    DepositoRepository depositoRepository;

    public static java.sql.Date dateNow() {
        return Date.valueOf(LocalDate.now());
    }

    public static Time timeNow() {
        return Time.valueOf(LocalTime.now());
    }
}
