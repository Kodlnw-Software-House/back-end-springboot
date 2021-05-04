package com.int221.finalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleExceptions(CustomException customException, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(customException.getErrorCode(),customException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
