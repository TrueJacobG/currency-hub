package com.truejacobg.currencyhub.favorite.dto;

import com.truejacobg.currencyhub.favorite.entity.FavoriteEntity;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class GetFavoriteResponseDTO {

    private String message;
    private List<FavoriteEntity> list;
    private HttpStatus status;

}