package com.truejacobg.currencyhub.currency;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.currency.dto.CurrencyDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyDateDTO;
import com.truejacobg.currencyhub.currency.dto.CurrencyResponseDTO;
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
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                try {
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode jsonNode = objectMapper.readTree(response.toString());

                    for (JsonNode element : jsonNode) {

                        JsonNode ratesArray = element.get("rates");

                        for (JsonNode rate : ratesArray) {

                            CurrencyDTO currencyDTO = new CurrencyDTO(rate.get("code").asText(), rate.get("currency").asText(), element.get("table").asText(), rate.get("mid").asDouble());
                            currencyDTOS.add(currencyDTO);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new CurrencyResponseDTO("Currency data fetch failed", HttpStatus.EXPECTATION_FAILED);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new CurrencyResponseDTO("Read api response fail", HttpStatus.EXPECTATION_FAILED);
            }

        }


        return new CurrencyResponseDTO("Currency rates fetch", currencyDTOS, HttpStatus.OK);
    }


    public CurrencyResponseDTO getCurrencyRateByCode(String currencyCode) {
        List<CurrencyDTO> currencyDTOS = new ArrayList<>();

        if (currencyRepository.findByCurrencyCode(currencyCode) != null) {
            String table = currencyRepository.findByCurrencyCode(currencyCode).getCurrencyTable();
            try {
                URL obj = new URL("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currencyCode + "/");
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                try {
                    ObjectMapper objectMapper = new ObjectMapper();

                    CurrencyDTO currencyDTO = new CurrencyDTO(currencyRepository.findByCurrencyCode(currencyCode).getCurrencyCode(), currencyRepository.findByCurrencyCode(currencyCode).getCurrencyName(),
                            currencyRepository.findByCurrencyCode(currencyCode).getCurrencyTable(), objectMapper.readTree(response.toString()).get("rates").get(0).get("mid").asDouble());
                    currencyDTOS.add(currencyDTO);

                } catch (Exception e) {
                    e.printStackTrace();
                    return new CurrencyResponseDTO("Currency data fetch failed", HttpStatus.EXPECTATION_FAILED);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            return new CurrencyResponseDTO("Wrong currency code", HttpStatus.BAD_REQUEST);
        }

        return new CurrencyResponseDTO("Currency fetch", currencyDTOS, HttpStatus.OK);
    }


    public CurrencyResponseDTO getCurrencyRateByCodeInDate(String currencyCode, String from, String end) {

        List<CurrencyDateDTO> currencyDateDTOS = new ArrayList<>();
        if (currencyRepository.findByCurrencyCode(currencyCode) != null) {
            String table = currencyRepository.findByCurrencyCode(currencyCode).getCurrencyTable();
            try {
                //TODO: add validation of path date ( from/end )
                //TODO: add check api response status code before fetch data
                URL obj = new URL("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currencyCode + "/" + from + "/" + end + "/");
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                try {
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode jsonNode = objectMapper.readTree(response.toString()).get("rates");

                    for (JsonNode element : jsonNode) {

                        CurrencyDateDTO currencyDateDTO = new CurrencyDateDTO(currencyRepository.findByCurrencyCode(currencyCode).getCurrencyCode(), currencyRepository.findByCurrencyCode(currencyCode).getCurrencyName(),
                                currencyRepository.findByCurrencyCode(currencyCode).getCurrencyTable(), element.get("mid").asDouble(), element.get("effectiveDate").asText());

                        currencyDateDTOS.add(currencyDateDTO);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new CurrencyResponseDTO("Currency data fetch failed", HttpStatus.EXPECTATION_FAILED);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            return new CurrencyResponseDTO("Wrong currency code", HttpStatus.BAD_REQUEST);
        }


        return new CurrencyResponseDTO("Currency fetch", currencyDateDTOS, HttpStatus.OK);
    }
}
