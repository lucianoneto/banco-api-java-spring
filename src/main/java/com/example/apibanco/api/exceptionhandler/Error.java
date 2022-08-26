package com.example.apibanco.api.exceptionhandler;

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
    private LocalDateTime dateTime;
    private String title;
    private HashMap<String, String> invalidCamps;

}