package com.truejacobg.currencyhub.currency.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CurrencyResponseDTO {
    private String message;
    private List<CurrencyDTO> list;
    private HttpStatus status;

    public CurrencyResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
