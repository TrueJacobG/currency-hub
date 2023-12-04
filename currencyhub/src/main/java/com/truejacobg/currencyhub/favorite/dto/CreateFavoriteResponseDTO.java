package com.truejacobg.currencyhub.favorite.dto;

import com.truejacobg.currencyhub.currency.dto.CurrencyDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CreateFavoriteResponseDTO {

    private String message;
    private List<CurrencyDTO> list;
    private HttpStatus status;
    public CreateFavoriteResponseDTO(String message, HttpStatus status){
        this.message = message;
        this.status = status;


    }
}
