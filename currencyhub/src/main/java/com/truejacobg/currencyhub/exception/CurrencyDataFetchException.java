package com.truejacobg.currencyhub.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDataFetchException extends GlobalException {
    private String message;


    public CurrencyDataFetchException(String message) {
        super(message);
    }
}
