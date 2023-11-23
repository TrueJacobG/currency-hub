package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends GlobalException {
    private final static HttpStatus defaultHttpStatus = HttpStatus.UNAUTHORIZED;

    public AuthorizationException(String errorMessage) {
        super(errorMessage, defaultHttpStatus);
    }

}
