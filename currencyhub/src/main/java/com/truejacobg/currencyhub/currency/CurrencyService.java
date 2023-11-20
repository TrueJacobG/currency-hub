package com.truejacobg.currencyhub.currency;


import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CurrencyService {

    private CurrencyRepository currencyRepository;


    public CurrencyResponseDTO getCurrencyRates() {

        //TODO: do realizacji w service currency

        return new CurrencyResponseDTO("response.body", HttpStatus.OK);
    }


}
