package com.truejacobg.currencyhub.favorite.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


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