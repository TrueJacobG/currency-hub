package com.truejacobg.currencyhub.currency;

import com.truejacobg.currencyhub.currency.dto.CurrencyDateResponseDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/rate/{currencyCode}")
    ResponseEntity<CurrencyResponseDTO> getCurrencyRateByCode(@PathVariable String currencyCode) {
        CurrencyResponseDTO responseDTO = currencyService.getCurrencyRateByCode(currencyCode);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}/{from}/{end}")
    ResponseEntity<CurrencyDateResponseDTO> getCurrencyRateByCodeInDate(@PathVariable String currencyCode, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        CurrencyDateResponseDTO responseDTO = currencyService.getCurrencyRateByCodeInDate(currencyCode, from, end);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}/week")
    ResponseEntity<CurrencyDateResponseDTO> getCurrencyRateCurrentWeek(@PathVariable String currencyCode) {
        CurrencyDateResponseDTO responseDTO = currencyService.getCurrencyRateCurrentWeek(currencyCode);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}/month")
    ResponseEntity<CurrencyDateResponseDTO> getCurrencyRateCurrentMonth(@PathVariable String currencyCode) {
        CurrencyDateResponseDTO responseDTO = currencyService.getCurrencyRateCurrentMonth(currencyCode);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/rate/{currencyCode}/year")
    ResponseEntity<CurrencyDateResponseDTO> getCurrencyRateCurrentYear(@PathVariable String currencyCode) {
        CurrencyDateResponseDTO responseDTO = currencyService.getCurrencyRateCurrentYear(currencyCode);
        return ResponseEntity.ok(responseDTO);
    }

}
