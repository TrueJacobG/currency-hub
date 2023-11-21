package com.truejacobg.currencyhub.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WrongCurrencyCodeException extends GlobalException {
    private String message;

    public WrongCurrencyCodeException(String message) {
        super(message);
    }
}
