package com.truejacobg.currencyhub.favorite.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class FavoriteDTO {
    private String currencyCode;
    private String userId;
    private String date;
}
