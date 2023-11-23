package com.truejacobg.currencyhub.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Setter
public class GlobalException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
