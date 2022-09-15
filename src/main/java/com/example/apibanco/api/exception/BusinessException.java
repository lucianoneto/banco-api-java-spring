package com.example.apibanco.api.exception;


import lombok.Getter;

import java.util.HashMap;

public class BusinessException extends RuntimeException {
    @Getter
    private HashMap<String, String> invalidFields;
    @Getter
    private String title;

    public BusinessException(String title, HashMap<String, String> invalidFields) {
        this.title = title;
        this.invalidFields = invalidFields;
    }
}
