package com.nonier.cliniccore.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Component
@RestControllerAdvice(basePackages = "com.nonier.cliniccore.controller")
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handle(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException exception) {
        return ResponseEntity.internalServerError()
                .body("Exception occured: %s cause: %s"
                        .formatted(exception.getClass(), exception.getMessage()));
    }
}