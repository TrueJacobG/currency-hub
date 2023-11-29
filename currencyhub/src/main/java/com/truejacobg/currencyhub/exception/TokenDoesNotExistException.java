package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class TokenDoesNotExistException extends GlobalException {
    private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public TokenDoesNotExistException(String message) {
        super(status, message);
    }
}
