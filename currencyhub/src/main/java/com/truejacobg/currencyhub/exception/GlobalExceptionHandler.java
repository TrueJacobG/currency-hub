package com.truejacobg.currencyhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public final ResponseEntity<ErrorResponse> somethingWentWrong(GlobalException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Wrong date format ( should be YYYY-MM-DD )", HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
