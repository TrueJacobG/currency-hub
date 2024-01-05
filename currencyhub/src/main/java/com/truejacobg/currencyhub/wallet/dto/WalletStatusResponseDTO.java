package com.truejacobg.currencyhub.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
public class WalletStatusResponseDTO {
    private String message;
    private List<WalletListElementDTO> walleList;
    private HttpStatus status;
}
