package com.truejacobg.currencyhub.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class CurrencyPointerDTO {
        private String currencyCode;
        private String currencyName;
        private String currencyTable;
        private Float mid;
        private Boolean pointerUP;
}
