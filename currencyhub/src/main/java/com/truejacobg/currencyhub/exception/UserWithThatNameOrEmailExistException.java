package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class UserWithThatNameOrEmailExistException extends GlobalException {
    private final static HttpStatus defaultHttpStatus = HttpStatus.BAD_REQUEST;

    public UserWithThatNameOrEmailExistException(String message) {
        super(message, defaultHttpStatus);
    }
}
