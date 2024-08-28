package com.emazon.stock_microservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ArticuloNullException.class)
    public ResponseEntity<String> handleArticuloNullException(ArticuloNullException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ArticuloSaveException.class)
    public ResponseEntity<String> handleArticuloSaveException(ArticuloSaveException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(ArticuloNotFoundException.class)
    public ResponseEntity<String> handleArticuloNotFoundException(ArticuloNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
