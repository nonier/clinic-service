package com.nonier.cliniccore.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@Component
@RestControllerAdvice(basePackages = "com.nonier.cliniccore.controller")
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handle(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException exception) {
        return ResponseEntity.internalServerError()
                .body("Exception occured: %s cause: %s"
                        .formatted(exception.getClass(), exception.getMessage()));
    }
}