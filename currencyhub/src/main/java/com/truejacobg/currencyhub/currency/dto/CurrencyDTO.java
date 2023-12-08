package com.truejacobg.currencyhub.currency.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data

public class CurrencyDTO {

    private String currencyCode;
    private String currencyName;
    private String currencyTable;
    private Float mid;


}
