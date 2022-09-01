package com.example.apibanco.api.exception;


import lombok.Getter;

import java.util.HashMap;

public class BusinessException extends RuntimeException {
    @Getter
    private final HashMap<String, String> invalidFields;
    @Getter
    private final String title;

    public BusinessException(String title, HashMap<String, String> invalidFields) {
        this.title = title;
        this.invalidFields = invalidFields;
    }
}
