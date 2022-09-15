package com.example.apibanco.api.exceptionhandler;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
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
import java.util.Locale;


@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> invalidFields = new HashMap<>();
        for (ObjectError problem : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) problem).getField();
            String message;
            message = problem.getDefaultMessage();
            invalidFields.put(name, message);
        }
        return handleExceptionInternal(ex, Error.builder()
                        .status(status.value())
                        .dateTime(LocalDateTime.now())
                        .title(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US))
                        .invalidFields(invalidFields)
                        .build()
                , headers, status, request);
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleNegocioViolation(BusinessException ex) {
        return new ResponseEntity<>(Error.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .title(ex.getTitle())
                .dateTime(LocalDateTime.now())
                .invalidFields(ex.getInvalidFields())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST.value());
    }
}
