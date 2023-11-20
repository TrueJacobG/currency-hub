package com.truejacobg.currencyhub.currency.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("currency")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CurrencyEntity {

    private String currencyCode;
    private String currencyName;
    private String currencyTable;
}
