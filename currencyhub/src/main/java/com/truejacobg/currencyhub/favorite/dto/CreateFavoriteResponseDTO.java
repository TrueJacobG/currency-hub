package com.truejacobg.currencyhub.favorite.dto;

import com.truejacobg.currencyhub.currency.dto.CurrencyDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Data
public class CreateFavoriteResponseDTO {

    private String message;
    private HttpStatus status;

    public CreateFavoriteResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
