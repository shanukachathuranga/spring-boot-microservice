package com.shanuka.microservice.handler;

import com.shanuka.microservice.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(BusinessException exp){
        return ResponseEntity.badRequest().body(exp.getMessage());
    }

}
