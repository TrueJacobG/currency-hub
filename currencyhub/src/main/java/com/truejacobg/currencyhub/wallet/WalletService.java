package com.truejacobg.currencyhub.wallet;

import com.truejacobg.currencyhub.currency.CurrencyRepository;
import com.truejacobg.currencyhub.currency.CurrencyService;
import com.truejacobg.currencyhub.exception.AddBalanceException;
import com.truejacobg.currencyhub.exception.NoEnoughBalanceInWallet;
import com.truejacobg.currencyhub.exception.WalletDoesntExistException;
import com.truejacobg.currencyhub.exception.WrongCurrencyCodeException;
import com.truejacobg.currencyhub.security.JWTDecoder;
import com.truejacobg.currencyhub.wallet.dto.*;
import com.truejacobg.currencyhub.wallet.entity.WalletCurrencyExchangeEntity;
import com.truejacobg.currencyhub.wallet.entity.WalletEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WalletService {

    private WalletRepository walletRepository;
    private WalletCurrencyExchangeRepository walletCurrencyExchangeRepository;
    private CurrencyService currencyService;
    private CurrencyRepository currencyRepository;

    public List<WalletListElementDTO> walletMapToListParser(Map<String, Double> walletMap) {
        List<WalletListElementDTO> walletList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : walletMap.entrySet()) {
            WalletListElementDTO walletListElementDTO = new WalletListElementDTO(entry.getKey(), entry.getValue());
            walletList.add(walletListElementDTO);
        }
        return walletList;
    }

    public WalletAddResponseDTO addBalance(WalletAddBalanceDTO value, HttpServletRequest servletRequest) {
        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);

        WalletEntity walletEntity;
        WalletCurrencyExchangeEntity walletCurrencyExchangeEntity;


        if (value.getValue() <= 0) {
            throw new AddBalanceException("Value you add must be greater then 0", HttpStatus.EXPECTATION_FAILED);
        } else {
            try {
                walletCurrencyExchangeEntity = walletCurrencyExchangeRepository.findByName(userName);
                walletEntity = walletRepository.findWalletEntityByName(userName);
                double currentValue = walletEntity.getWalletMap().get("PLN");
                walletEntity.getWalletMap().put("PLN", currentValue + value.getValue());
                WalletCurrencyExchangeDTO walletCurrencyExchangeDTO = new WalletCurrencyExchangeDTO("-", "PLN", value.getValue(), LocalDateTime.now());
                walletCurrencyExchangeEntity.getExchangeCurrencyDTOList().add(walletCurrencyExchangeDTO);
            } catch (NullPointerException e) {
                Map<String, Double> walletMap = new HashMap<>();
                List<WalletCurrencyExchangeDTO> currencyExchangeDTOList = new ArrayList<>();
                walletMap.put("PLN", value.getValue());
                walletEntity = new WalletEntity(userName, walletMap);
                WalletCurrencyExchangeDTO walletCurrencyExchangeDTO = new WalletCurrencyExchangeDTO("-", "PLN", value.getValue(), LocalDateTime.now());
                currencyExchangeDTOList.add(walletCurrencyExchangeDTO);
                walletCurrencyExchangeEntity = new WalletCurrencyExchangeEntity(userName, currencyExchangeDTOList);
            }
            walletCurrencyExchangeRepository.save(walletCurrencyExchangeEntity);
            walletRepository.save(walletEntity);

        }
        return new WalletAddResponseDTO("Balance added successfully", HttpStatus.OK, walletEntity.getWalletMap().get("PLN"));
    }

    public WalletStatusResponseDTO getWalletStatus(HttpServletRequest servletRequest) {
        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);
        Map<String, Double> walletMap;

        try {
            WalletEntity walletEntity = walletRepository.findWalletEntityByName(userName);
            walletMap = walletEntity.getWalletMap();
        } catch (NullPointerException e) {
            throw new WalletDoesntExistException("There is no wallet for this user", HttpStatus.EXPECTATION_FAILED);
        }

        return new WalletStatusResponseDTO("Current wallet status returned", walletMapToListParser(walletMap), HttpStatus.OK);
    }

    public WalletResponseDTO exchangeCurrency(WalletCurrencyExchangeDTO currencyExchangeDTO, HttpServletRequest servletRequest) {
        if (currencyExchangeDTO.getCurrencyCodeFrom().equals(currencyExchangeDTO.getCurrencyCodeTo()))
            throw new WrongCurrencyCodeException("CurrencyCodeFrom can't be same as CurrencyCodeTo", HttpStatus.EXPECTATION_FAILED);

        if (currencyExchangeDTO.getCurrencyCodeFrom().equals("PLN")) {
            currencyRepository.findByCurrencyCode(currencyExchangeDTO.getCurrencyCodeTo()).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currencyCodeTo", HttpStatus.NOT_FOUND));
        } else if (currencyExchangeDTO.getCurrencyCodeTo().equals("PLN")) {
            currencyRepository.findByCurrencyCode(currencyExchangeDTO.getCurrencyCodeFrom()).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currencyCodeFrom", HttpStatus.NOT_FOUND));
        } else {
            currencyRepository.findByCurrencyCode(currencyExchangeDTO.getCurrencyCodeFrom()).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currencyCodeFrom", HttpStatus.NOT_FOUND));
            currencyRepository.findByCurrencyCode(currencyExchangeDTO.getCurrencyCodeTo()).orElseThrow(() -> new WrongCurrencyCodeException("Wrong currencyCodeTo", HttpStatus.NOT_FOUND));
        }

        JWTDecoder jwtDecoder = new JWTDecoder(servletRequest);
        String userName = jwtDecoder.getNameFromToken(servletRequest);

        WalletEntity walletEntity;

        Double midTo;
        Double midFrom;

        try {
            walletEntity = walletRepository.findWalletEntityByName(userName);

            if (walletEntity == null)
                throw new WalletDoesntExistException("There is no wallet for this user, you should add balance to create it", HttpStatus.EXPECTATION_FAILED);

            if (walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) == null)
                throw new NoEnoughBalanceInWallet("You don't have currencyFrom in your wallet", HttpStatus.NOT_FOUND);

            WalletCurrencyExchangeEntity walletCurrencyExchangeEntity = walletCurrencyExchangeRepository.findByName(userName);

            if (currencyExchangeDTO.getCurrencyCodeFrom().equals("PLN")) {
                midTo = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeTo()).getList().get(0).getMid();
                midFrom = 1.00;

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) - (currencyExchangeDTO.getValue() * midTo)*1.0019);
                }

            } else if (currencyExchangeDTO.getCurrencyCodeTo().equals("PLN")) {
                midTo = 1.00;
                midFrom = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeFrom()).getList().get(0).getMid();

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), (walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) - (currencyExchangeDTO.getValue() / midFrom)*1.0019));
                }
            } else {
                midTo = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeTo()).getList().get(0).getMid();
                midFrom = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeFrom()).getList().get(0).getMid();

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom())) * midFrom - (currencyExchangeDTO.getValue() * midTo)*1.0019) / midFrom);
                }
            }

            WalletCurrencyExchangeDTO walletCurrencyExchangeDTO = new WalletCurrencyExchangeDTO(currencyExchangeDTO.getCurrencyCodeFrom(), currencyExchangeDTO.getCurrencyCodeTo(),
                    currencyExchangeDTO.getValue(), LocalDateTime.now());

            walletCurrencyExchangeEntity.getExchangeCurrencyDTOList().add(walletCurrencyExchangeDTO);

            walletCurrencyExchangeRepository.save(walletCurrencyExchangeEntity);
            walletRepository.save(walletEntity);

        } catch (NullPointerException e) {
            try {
                walletEntity = walletRepository.findWalletEntityByName(userName);
            } catch (NullPointerException ex) {
                throw new WalletDoesntExistException("There is no wallet for this user, you should add balance to create it", HttpStatus.EXPECTATION_FAILED);
            }
            List<WalletCurrencyExchangeDTO> currencyExchangeDTOList = new ArrayList<>();

            if (currencyExchangeDTO.getCurrencyCodeFrom().equals("PLN")) {
                midTo = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeTo()).getList().get(0).getMid();
                midFrom = 1.00;

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) - currencyExchangeDTO.getValue() * midTo);
                }

            } else if (currencyExchangeDTO.getCurrencyCodeTo().equals("PLN")) {
                midTo = 1.00;
                midFrom = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeFrom()).getList().get(0).getMid();

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), (walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) - currencyExchangeDTO.getValue() / midFrom));
                }
            } else {
                midTo = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeTo()).getList().get(0).getMid();
                midFrom = currencyService.getCurrencyRateByCode(currencyExchangeDTO.getCurrencyCodeFrom()).getList().get(0).getMid();

                if ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom()) * midFrom) < currencyExchangeDTO.getValue() * midTo) {
                    throw new NoEnoughBalanceInWallet("You don't have enough balance, try change currencyFrom or add balance", HttpStatus.EXPECTATION_FAILED);
                } else {
                    walletEntity.getWalletMap().merge(currencyExchangeDTO.getCurrencyCodeTo(), currencyExchangeDTO.getValue(), Double::sum);
                    walletEntity.getWalletMap().put(currencyExchangeDTO.getCurrencyCodeFrom(), ((walletEntity.getWalletMap().get(currencyExchangeDTO.getCurrencyCodeFrom())) * midFrom - currencyExchangeDTO.getValue() * midTo) / midFrom);
                }
            }

            WalletCurrencyExchangeDTO walletCurrencyExchangeDTO = new WalletCurrencyExchangeDTO(currencyExchangeDTO.getCurrencyCodeFrom(), currencyExchangeDTO.getCurrencyCodeTo(),
                    currencyExchangeDTO.getValue(), LocalDateTime.now());

            currencyExchangeDTOList.add(walletCurrencyExchangeDTO);

            WalletCurrencyExchangeEntity walletCurrencyExchangeEntity = new WalletCurrencyExchangeEntity(userName, currencyExchangeDTOList);

            walletCurrencyExchangeRepository.save(walletCurrencyExchangeEntity);
            walletRepository.save(walletEntity);

        }

        return new WalletResponseDTO("Exchange status ok", HttpStatus.OK);
    }
}

