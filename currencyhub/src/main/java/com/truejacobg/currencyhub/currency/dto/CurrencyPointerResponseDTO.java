package com.truejacobg.currencyhub.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@Data
public class CurrencyPointerResponseDTO {
    private String message;
    private List<CurrencyPointerDTO> list;
    private HttpStatus status;
}
