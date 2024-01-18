package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class CurrencyNotFoundException extends GlobalException {
    public CurrencyNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
