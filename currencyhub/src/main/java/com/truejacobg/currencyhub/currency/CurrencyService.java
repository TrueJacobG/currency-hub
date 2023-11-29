package com.truejacobg.currencyhub.currency;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.currency.dto.CurrencyDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyDateDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyDateResponseDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
import com.truejacobg.currencyhub.exception.CurrencyApiReadFail;
import com.truejacobg.currencyhub.exception.CurrencyDataFetchException;
import com.truejacobg.currencyhub.exception.WrongCurrencyCodeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CurrencyService {
    CurrencyRepository currencyRepository;

    public CurrencyResponseDTO getCurrencyRates() {

        String[] url = {"https://api.nbp.pl/api/exchangerates/tables/A", "https://api.nbp.pl/api/exchangerates/tables/B"};
        List<CurrencyDTO> currencyDTOS = new ArrayList<>();
        for (String s : url) {
            try {

                URL obj = new URL(s);
                obj.openConnection();

                try {
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode jsonNode = objectMapper.readTree(obj);

                    for (JsonNode element : jsonNode) {

                        JsonNode ratesArray = element.get("rates");

                        for (JsonNode rate : ratesArray) {

                            CurrencyDTO currencyDTO = new CurrencyDTO(rate.get("code").asText(), rate.get("currency").asText(), element.get("table").asText(), rate.get("mid").asDouble());
                            currencyDTOS.add(currencyDTO);
                        }
                    }
                } catch (Exception e) {
                    throw new CurrencyDataFetchException("Currency data fetch fail", HttpStatus.EXPECTATION_FAILED);
                }
            } catch (Exception e) {
                throw new CurrencyApiReadFail("Read data from API fail", HttpStatus.EXPECTATION_FAILED);
            }

        }


        return new CurrencyResponseDTO("Currency rates fetch", currencyDTOS, HttpStatus.OK);
    }


    public CurrencyResponseDTO getCurrencyRateByCode(String currencyCode) {
        List<CurrencyDTO> currencyDTOS = new ArrayList<>();

        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));
        try {
            URL obj = new URL("http://api.nbp.pl/api/exchangerates/rates/" + currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable()
                    + "/" + currencyCode + "/");
            obj.openConnection();

            try {
                ObjectMapper objectMapper = new ObjectMapper();

                CurrencyDTO currencyDTO = new CurrencyDTO(currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyCode(), currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyName(),
                        currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable(), objectMapper.readTree(obj).get("rates").get(0).get("mid").asDouble());
                currencyDTOS.add(currencyDTO);

            } catch (Exception e) {
                throw new CurrencyDataFetchException("Currency data fetch fail", HttpStatus.EXPECTATION_FAILED);
            }

        } catch (Exception e) {
            throw new CurrencyApiReadFail("Read data from API fail", HttpStatus.EXPECTATION_FAILED);
        }


        return new CurrencyResponseDTO("Currency fetch", currencyDTOS, HttpStatus.OK);
    }


    public CurrencyDateResponseDTO getCurrencyRateByCodeInDate(String currencyCode, String from, String end) {

        List<CurrencyDateDTO> currencyDateDTOS = new ArrayList<>();
        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));
        try {
            //TODO: add validation of path date ( from/end )
            //TODO: add check api response status code before fetch data
            URL obj = new URL("http://api.nbp.pl/api/exchangerates/rates/" + currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable() +
                    "/" + currencyCode + "/" + from + "/" + end + "/");
            obj.openConnection();

            try {
                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode = objectMapper.readTree(obj).get("rates");

                for (JsonNode element : jsonNode) {

                    CurrencyDateDTO currencyDateDTO = new CurrencyDateDTO(currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyCode(), currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyName(),
                            currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable(), element.get("mid").asDouble(), element.get("effectiveDate").asText());

                    currencyDateDTOS.add(currencyDateDTO);

                }
            } catch (Exception e) {
                throw new CurrencyDataFetchException("Currency data fetch failed", HttpStatus.EXPECTATION_FAILED);
            }

        } catch (Exception e) {
            throw new CurrencyApiReadFail("Currency api read fail", HttpStatus.EXPECTATION_FAILED);
        }


        return new CurrencyDateResponseDTO("Currency fetch", currencyDateDTOS, HttpStatus.OK);
    }
}
