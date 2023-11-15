package com.truejacobg.currencyhub.currency.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CurrencyReponseDTO {
    private String message;
    private String body;
    private HttpStatus status;
    public CurrencyReponseDTO(String message, HttpStatus status){
        this.message=message;
        this.status=status;
    }
}
