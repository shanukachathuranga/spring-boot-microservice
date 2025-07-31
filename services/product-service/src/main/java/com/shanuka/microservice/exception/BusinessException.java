package com.shanuka.microservice.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }
}
