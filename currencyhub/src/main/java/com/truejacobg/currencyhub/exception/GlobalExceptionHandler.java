package com.truejacobg.currencyhub.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public final ResponseEntity<ErrorResponse> somethingWentWrong(GlobalException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }


}
