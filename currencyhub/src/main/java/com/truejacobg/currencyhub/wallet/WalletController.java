package com.truejacobg.currencyhub.wallet;

import com.truejacobg.currencyhub.wallet.dto.WalletAddBalanceDTO;
import com.truejacobg.currencyhub.wallet.dto.WalletCurrencyExchangeDTO;
import com.truejacobg.currencyhub.wallet.dto.WalletResponseDTO;
import com.truejacobg.currencyhub.wallet.dto.WalletStatusResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/wallet")
@RestController
public class WalletController {
    private WalletService walletService;

    @PostMapping("/transfer")
    ResponseEntity<WalletResponseDTO> addBalance(@RequestBody WalletAddBalanceDTO value, HttpServletRequest servletRequest) {
        WalletResponseDTO walletResponseDTO = walletService.addBalance(value, servletRequest);
        return ResponseEntity.ok(walletResponseDTO);
    }

    @GetMapping("/")
    ResponseEntity<WalletStatusResponseDTO> getWalletStatus(HttpServletRequest servletRequest) {
        WalletStatusResponseDTO walletStatusResponseDTO = walletService.getWalletStatus(servletRequest);
        return ResponseEntity.ok(walletStatusResponseDTO);
    }

    @PostMapping("/exchange")
    ResponseEntity<WalletResponseDTO> exchangeCurrency(@RequestBody WalletCurrencyExchangeDTO currencyExchangeDTO, HttpServletRequest servletRequest) {
        WalletResponseDTO walletResponseDTO = walletService.exchangeCurrency(currencyExchangeDTO, servletRequest);
        return ResponseEntity.ok(walletResponseDTO);
    }
}
