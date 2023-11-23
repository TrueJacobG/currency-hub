package com.truejacobg.currencyhub.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CurrencyDateResponseDTO {
    private String message;
    private List<CurrencyDateDTO> list;
    private HttpStatus status;
}
