package com.truejacobg.currencyhub.favorite.dto;

import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Data
public class GetFavoriteResponseDTO {

    private String message;
    private List<FavoriteEntity> list;
    private HttpStatus status;
    public GetFavoriteResponseDTO(String message, List<FavoriteEntity> favoriteCurrencies, HttpStatus status){
        this.message = message;
        this.status = status;
        this.list = favoriteCurrencies;


    }
}