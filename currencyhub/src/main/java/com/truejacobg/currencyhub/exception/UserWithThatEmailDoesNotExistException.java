package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class UserWithThatEmailDoesNotExistException extends GlobalException {
    private final static HttpStatus defaultHttpStatus = HttpStatus.UNAUTHORIZED;

    public UserWithThatEmailDoesNotExistException(String message) {
        super(message, defaultHttpStatus);
    }
}
