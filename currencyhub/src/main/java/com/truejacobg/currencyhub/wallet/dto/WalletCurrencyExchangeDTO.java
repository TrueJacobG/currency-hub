package com.truejacobg.currencyhub.wallet.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
public class WalletCurrencyExchangeDTO {
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private Double value;
    private LocalDateTime localDateTime;
}
