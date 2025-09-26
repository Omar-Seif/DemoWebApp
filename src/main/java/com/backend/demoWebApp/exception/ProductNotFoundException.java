package com.backend.demoWebApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // ← Automatically returns 404 HTTP status

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    // Convenience constructor
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
}
