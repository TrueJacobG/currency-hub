package com.truejacobg.currencyhub.favorite.dto;

import com.truejacobg.currencyhub.currency.dto.CurrencyDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;



@NoArgsConstructor
@Setter
@Getter
@Data
public class FavoriteResponseDTO {
    private String message;
    private HttpStatus status;

    public FavoriteResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}