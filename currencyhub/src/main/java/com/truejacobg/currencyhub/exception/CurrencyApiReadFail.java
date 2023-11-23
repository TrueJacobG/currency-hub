package com.truejacobg.currencyhub.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CurrencyApiReadFail extends GlobalException {
    public CurrencyApiReadFail(String message, HttpStatus status) {
        super(message, status);
    }
}
