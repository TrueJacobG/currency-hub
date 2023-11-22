package com.truejacobg.currencyhub.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class WrongCurrencyCodeException extends GlobalException {
    public WrongCurrencyCodeException(String message, HttpStatus status) {
        super(message, status);
    }
}
