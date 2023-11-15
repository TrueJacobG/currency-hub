package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.dto.CurrencyReponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/currency")
@RestController
public class CurrencyController {

    private CurrencyService currencyService;

    @GetMapping("/rate")
    ResponseEntity<CurrencyReponseDTO> getCurrencyRates(){
        CurrencyReponseDTO reponseDTO=currencyService.getCurrencyRates();
        return ResponseEntity.ok(reponseDTO);
    }
}
