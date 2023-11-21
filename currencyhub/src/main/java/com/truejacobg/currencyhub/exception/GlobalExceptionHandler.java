package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public final ResponseEntity<AuthenticationFailResponse> authFailException(GlobalException exception) {
        AuthenticationFailResponse authenticationFailResponse = new AuthenticationFailResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(authenticationFailResponse, HttpStatus.UNAUTHORIZED);
    }
    //TODO: fix returned status ( we need HTTPStatus from throw new sampleException("message",HTTPStatus.Example) ) sprint3


}
