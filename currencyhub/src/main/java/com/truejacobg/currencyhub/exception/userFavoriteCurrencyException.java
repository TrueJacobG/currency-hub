package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class userFavoriteCurrencyException extends GlobalException{
    public userFavoriteCurrencyException(String message, HttpStatus status){
        super(message,status);

    }
}