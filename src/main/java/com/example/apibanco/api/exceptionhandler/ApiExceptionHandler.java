package com.example.apibanco.api.exceptionhandler;

import com.example.apibanco.api.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> invalidCamps = new HashMap<>();
        for (ObjectError problem : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) problem).getField();
            String message = problem.getDefaultMessage();
            invalidCamps.put(name, message);
        }
        return handleExceptionInternal(ex, Error.builder()
                        .status(status.value())
                        .dateTime(LocalDateTime.now())
                        .title("One or more fields are invalid. Please fill in correctly and try again.")
                        .invalidCamps(invalidCamps)
                        .build()
                , headers, status, request);
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleNegocioViolation(BusinessException ex) {
        return new ResponseEntity<Object>(Error.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title(ex.getTitle())
                .dateTime(LocalDateTime.now())
                .invalidCamps(ex.getInvalidCamps())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST.value());
    }
}
