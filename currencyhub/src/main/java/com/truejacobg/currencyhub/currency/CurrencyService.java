package com.truejacobg.currencyhub.currency;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.currency.dto.CurrencyReponseDTO;
import com.truejacobg.currencyhub.currency.entity.CurrencyEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@AllArgsConstructor
public class CurrencyService {

    private CurrencyRepository currencyRepository;


    public CurrencyReponseDTO getCurrencyRates(){

        //TODO: sarevice

        return new CurrencyReponseDTO("response.body", HttpStatus.OK);
    }



}
