package com.truejacobg.currencyhub.exception;

public class UserWithThatEmailDoesNotExistException extends GlobalException {
    public UserWithThatEmailDoesNotExistException(String message) {
        super(message);
    }
}
