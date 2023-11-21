package com.truejacobg.currencyhub.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException {
    //TODO: add field HTTP status sprint 3
    public GlobalException(String message) {
        super(message);

    }
}
