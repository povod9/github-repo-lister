package com.povod9.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorMessage> handleNotFound(HttpClientErrorException.NotFound e){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(404)
                .message("User not found on GitHub")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }


}
