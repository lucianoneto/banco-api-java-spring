package com.example.apibanco.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
@Builder
public class Error {

    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private Map<String, String> invalidFields;

}