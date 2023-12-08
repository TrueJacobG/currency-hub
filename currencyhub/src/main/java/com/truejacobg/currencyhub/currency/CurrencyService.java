package com.truejacobg.currencyhub.currency;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truejacobg.currencyhub.currency.dto.*;
import com.truejacobg.currencyhub.exception.CurrencyApiReadFail;
import com.truejacobg.currencyhub.exception.CurrencyDataFetchException;
import com.truejacobg.currencyhub.exception.WrongCurrencyCodeException;
import com.truejacobg.currencyhub.exception.WrongDateException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
@AllArgsConstructor
public class CurrencyService {
    CurrencyRepository currencyRepository;

    public CurrencyPointerResponseDTO getCurrencyRates() {

        String[] url = {"https://api.nbp.pl/api/exchangerates/tables/A", "https://api.nbp.pl/api/exchangerates/tables/B"};
        List<CurrencyPointerDTO> currencyPointerDTOS = new ArrayList<>();
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
                            URL getPointerURL = new URL("http://api.nbp.pl/api/exchangerates/rates/" + element.get("table").asText() +
                                    "/" + rate.get("code").asText() + "/last/2/");

                            getPointerURL.openConnection();

                            JsonNode getPointerJsonNodeRate = objectMapper.readTree(getPointerURL).get("rates");

                            double midLast = 0;
                            double midCurrent = 0;

                            for (JsonNode jRate : getPointerJsonNodeRate) {
                                if (midLast == 0) {
                                    midLast = jRate.get("mid").asDouble();
                                } else {
                                    midCurrent = jRate.get("mid").asDouble();
                                }
                            }
                            //TODO: get mid value always in format X.YZVC

                            boolean pointerUP;

                            pointerUP = !(midLast >= midCurrent);

                            CurrencyPointerDTO currencyPointerDTO = new CurrencyPointerDTO(rate.get("code").asText(), rate.get("currency").asText(), element.get("table").asText(), rate.get("mid").floatValue(), pointerUP);
                            currencyPointerDTOS.add(currencyPointerDTO);
                        }
                    }
                } catch (Exception e) {
                    throw new CurrencyDataFetchException("Currency data fetch fail", HttpStatus.EXPECTATION_FAILED);
                }
            } catch (Exception e) {
                throw new CurrencyApiReadFail("Read data from API fail", HttpStatus.EXPECTATION_FAILED);
            }

        }


        return new CurrencyPointerResponseDTO("Currency rates fetch", currencyPointerDTOS, HttpStatus.OK);
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
                        currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable(), objectMapper.readTree(obj).get("rates").get(0).get("mid").floatValue());
                currencyDTOS.add(currencyDTO);

            } catch (Exception e) {
                throw new CurrencyDataFetchException("Currency data fetch fail", HttpStatus.EXPECTATION_FAILED);
            }

        } catch (Exception e) {
            throw new CurrencyApiReadFail("Read data from API fail", HttpStatus.EXPECTATION_FAILED);
        }


        return new CurrencyResponseDTO("Currency fetch", currencyDTOS, HttpStatus.OK);
    }


    public CurrencyDateResponseDTO getCurrencyRateByCodeInDate(String currencyCode, LocalDate from, LocalDate end) {

        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));

        if (from.isAfter(end))
            throw new WrongDateException("Date from must be before end", HttpStatus.EXPECTATION_FAILED);

        List<CurrencyDateDTO> currencyDateDTOS = new ArrayList<>();

        try {
            URL obj = new URL("http://api.nbp.pl/api/exchangerates/rates/" + currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable() +
                    "/" + currencyCode + "/" + from + "/" + end + "/");
            obj.openConnection();

            try {
                ObjectMapper objectMapper = new ObjectMapper();

                JsonNode jsonNode = objectMapper.readTree(obj).get("rates");

                for (JsonNode element : jsonNode) {

                    CurrencyDateDTO currencyDateDTO = new CurrencyDateDTO(currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyCode(), currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyName(),
                            currencyRepository.findDataByCurrencyCode(currencyCode).getCurrencyTable(), element.get("mid").floatValue(), element.get("effectiveDate").asText());

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

    public CurrencyDateResponseDTO getCurrencyRateCurrentWeek(String currencyCode) {
        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        LocalDate from = LocalDate.parse(dateFormat.format(calendar.getTime()));

        return getCurrencyRateByCodeInDate(currencyCode, from, end);
    }

    public CurrencyDateResponseDTO getCurrencyRateCurrentMonth(String currencyCode) {
        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -1);
        LocalDate from = LocalDate.parse(dateFormat.format(calendar.getTime()));

        return getCurrencyRateByCodeInDate(currencyCode, from, end);
    }

    public CurrencyDateResponseDTO getCurrencyRateCurrentYear(String currencyCode) {
        currencyRepository.findByCurrencyCode(currencyCode).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currency code", HttpStatus.NOT_FOUND));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate end = LocalDate.parse(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.YEAR, -1);
        LocalDate from = LocalDate.parse(dateFormat.format(calendar.getTime()));

        return getCurrencyRateByCodeInDate(currencyCode, from, end);
    }
}


