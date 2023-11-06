package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class GlobalException extends RuntimeException{
    public GlobalException(String message){
        super(message);
    }
}
