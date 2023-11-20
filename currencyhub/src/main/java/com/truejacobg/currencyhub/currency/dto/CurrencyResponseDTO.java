package com.truejacobg.currencyhub.currency.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CurrencyResponseDTO {
    private String message;
    private String body;
    private HttpStatus status;

    public CurrencyResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
