package com.example.apibanco.api.exception;


import lombok.Getter;

import java.util.Map;

public class BusinessException extends RuntimeException {
    @Getter
    private final Map<String, String> invalidFields;
    @Getter
    private final String title;

    public BusinessException(String title, Map<String, String> invalidFields) {
        this.title = title;
        this.invalidFields = invalidFields;
    }
}
