package com.truejacobg.currencyhub.exception;

public class AuthorizationException  extends RuntimeException{
    public AuthorizationException(String errorMessage){
        super(errorMessage);
    }

}
