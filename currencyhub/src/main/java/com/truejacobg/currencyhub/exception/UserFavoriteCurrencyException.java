package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class UserFavoriteCurrencyException extends GlobalException {
    public UserFavoriteCurrencyException(String message, HttpStatus status) {
        super(message, status);

    }
}