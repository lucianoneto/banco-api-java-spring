package com.example.apibanco.exceptionhandler;

import com.example.apibanco.exception.NegocioException;
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
        HashMap<String, String> campos = new HashMap<>();
        for (ObjectError problem : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) problem).getField();
            String mensagem = problem.getDefaultMessage();
            campos.put(nome, mensagem);
        }
        return handleExceptionInternal(ex, Error.builder()
                        .status(status.value())
                        .dataHora(LocalDateTime.now())
                        .titulo("One or more fields are invalid. Please fill in correctly and try again.")
                        .camposInvalidos(campos)
                        .build()
                , headers, status, request);
    }

    @ExceptionHandler({NegocioException.class})
    protected ResponseEntity<Object> handleNegocioViolation(NegocioException ex) {
        return new ResponseEntity<Object>(Error.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .titulo(ex.getTitulo())
                .dataHora(LocalDateTime.now())
                .camposInvalidos(ex.getCamposInvalidos())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST.value());
    }


}
