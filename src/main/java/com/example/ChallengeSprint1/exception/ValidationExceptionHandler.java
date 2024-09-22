package com.example.ChallengeSprint1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Define que a exceção retorna o status 404 (Not Found)

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidationExceptionHandler extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationExceptionHandler(String message) {
        super(message);
    }
}
