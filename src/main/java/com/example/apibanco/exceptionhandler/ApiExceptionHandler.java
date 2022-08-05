package com.example.apibanco.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Campo> campos = new ArrayList<>();
        for(ObjectError problem : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) problem).getField();
            String mensagem = problem.getDefaultMessage();
            campos.add(new Error.Campo(nome,mensagem));
        }

        Error error = new Error();
        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitulo("One or more fields are invalid. Please fill in correctly and try again.");
        error.setCampos(campos);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

}
