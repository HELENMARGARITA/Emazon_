package com.emazon.stock_microservice.application;

public class ArticuloNullException extends RuntimeException {
    public ArticuloNullException(String message) {
        super(message);
    }
}

