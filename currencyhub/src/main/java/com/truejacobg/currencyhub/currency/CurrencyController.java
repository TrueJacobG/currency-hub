package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/currency")
@RestController
public class CurrencyController {

    private CurrencyService currencyService;

    @GetMapping("/rate")
    ResponseEntity<CurrencyResponseDTO> getCurrencyRates() {
        CurrencyResponseDTO responseDTO = currencyService.getCurrencyRates();
        return ResponseEntity.ok(responseDTO);
    }
}
