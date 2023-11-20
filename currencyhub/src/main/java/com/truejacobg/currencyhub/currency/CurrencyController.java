package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/currency")
@RestController
public class CurrencyController {

    private CurrencyService currencyService;

    @GetMapping("/rate")
    ResponseEntity<CurrencyResponseDTO> getCurrencyRates() throws IOException, XMLStreamException {
        CurrencyResponseDTO responseDTO = currencyService.getCurrencyRates();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}")
    ResponseEntity<CurrencyResponseDTO> getCurrencyRateByCode(@PathVariable String currencyCode) {
        CurrencyResponseDTO responseDTO = currencyService.getCurrencyRateByCode(currencyCode);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}/{from}/{end}")
    ResponseEntity<CurrencyResponseDTO> getCurrencyRateByCodeInDate(@PathVariable String currencyCode, @PathVariable String from, @PathVariable String end) {
        CurrencyResponseDTO responseDTO = currencyService.getCurrencyRateByCodeInDate(currencyCode, from, end);
        return ResponseEntity.ok(responseDTO);
    }


}
