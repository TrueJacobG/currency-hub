package com.truejacobg.currencyhub.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class WalletAddResponseDTO {
    private String message;
    private HttpStatus status;
    private Double newWalletValue;
}
