package com.example.mrec.mrec_demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleGlobalException {
    Logger log = LoggerFactory.getLogger(HandleGlobalException.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
        MethodArgumentNotValidException e
    ){
        Map<String,String> errorMap = new HashMap<>();
        
        errorMap.put("Error ", "Invalid input format");

        
        e.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        );

        log.error("Errors : "+errorMap);

        return ResponseEntity.badRequest().body(errorMap);
    }
}
