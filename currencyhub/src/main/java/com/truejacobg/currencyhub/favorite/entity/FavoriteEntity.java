package com.truejacobg.currencyhub.favorite.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("favorite")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class FavoriteEntity {
    private String currencyCode;
    private String name;
    private LocalDate date;
}
