package com.truejacobg.currencyhub.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyApiReadFail extends GlobalException {
    public CurrencyApiReadFail(String message) {
        super(message);
    }
}
