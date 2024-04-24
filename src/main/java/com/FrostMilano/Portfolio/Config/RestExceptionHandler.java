package com.FrostMilano.Portfolio.config;

import com.FrostMilano.Portfolio.dtos.ErrorDto;
import com.FrostMilano.Portfolio.exceptions.AppException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException ex) {
        // Log the exception if needed
        ex.printStackTrace();

        // Return a response with a suitable message
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired. Please log in again.");
    }
}
