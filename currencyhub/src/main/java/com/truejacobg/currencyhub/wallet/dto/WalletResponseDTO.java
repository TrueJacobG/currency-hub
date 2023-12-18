package com.truejacobg.currencyhub.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
@AllArgsConstructor
@Data
public class WalletResponseDTO {
    private String message;
    private HttpStatus status;
}
