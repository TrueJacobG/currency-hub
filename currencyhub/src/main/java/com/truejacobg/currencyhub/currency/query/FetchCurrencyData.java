package com.truejacobg.currencyhub.currency.query;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.currency.CurrencyRepository;
import com.truejacobg.currencyhub.currency.dto.CurrencyReponseDTO;
import com.truejacobg.currencyhub.currency.entity.CurrencyEntity;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchCurrencyData {
    private CurrencyRepository currencyRepository;
    public CurrencyReponseDTO fetchDataAndSaveToMongoDB() {
        currencyRepository.deleteAll();
        try {
            try { // Table A
                String urlA = "https://api.nbp.pl/api/exchangerates/tables/A";

                URL objA = new URL(urlA);
                HttpURLConnection connectionA = (HttpURLConnection) objA.openConnection();
                BufferedReader inA = new BufferedReader(
                        new InputStreamReader(connectionA.getInputStream())
                );
                String inputLineA;
                StringBuffer responseA = new StringBuffer();
                while ((inputLineA = inA.readLine()) != null) {
                    responseA.append(inputLineA);
                }
                inA.close();
                try {
                    ObjectMapper objectMapperA = new ObjectMapper();

                    JsonNode jsonNodeA = objectMapperA.readTree(responseA.toString());

                    for (JsonNode elementA : jsonNodeA) {

                        String table = elementA.get("table").asText();

                        System.out.println("Table: " + table);

                        JsonNode ratesArrayA = elementA.get("rates");

                        for (JsonNode rateA : ratesArrayA) {

                            CurrencyEntity currencyEntity = new CurrencyEntity(rateA.get("code").asText(), rateA.get("currency").asText(), elementA.get("table").asText());
                            currencyRepository.save(currencyEntity);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new CurrencyReponseDTO("Currency data fatched failed", HttpStatus.EXPECTATION_FAILED);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CurrencyReponseDTO("Currency data fatched failed on A", HttpStatus.EXPECTATION_FAILED);
            }
            try { // Table B
                String urlB = "https://api.nbp.pl/api/exchangerates/tables/B";

                URL objB = new URL(urlB);
                HttpURLConnection connectionB = (HttpURLConnection) objB.openConnection();
                BufferedReader inB = new BufferedReader(
                        new InputStreamReader(connectionB.getInputStream())
                );
                String inputLineB;
                StringBuffer responseB = new StringBuffer();
                while ((inputLineB = inB.readLine()) != null) {
                    responseB.append(inputLineB);
                }
                inB.close();
                try {
                    ObjectMapper objectMapperB = new ObjectMapper();

                    JsonNode jsonNodeB = objectMapperB.readTree(responseB.toString());

                    for (JsonNode elementB : jsonNodeB) {

                        String tableB = elementB.get("table").asText();

                        System.out.println("Table: " + tableB);

                        JsonNode ratesArrayB = elementB.get("rates");

                        for (JsonNode rateB : ratesArrayB) {
                            CurrencyEntity currencyEntity = new CurrencyEntity(rateB.get("code").asText(), rateB.get("currency").asText(), elementB.get("table").asText());
                            currencyRepository.save(currencyEntity);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new CurrencyReponseDTO("Currency data fatched failed", HttpStatus.EXPECTATION_FAILED);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CurrencyReponseDTO("Currency data fatched failed on B", HttpStatus.EXPECTATION_FAILED);
            }

            return new CurrencyReponseDTO("Currency data fatched and saved from table A and B", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new CurrencyReponseDTO("Currency data fatched failed", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
