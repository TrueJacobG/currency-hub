package com.truejacobg.currencyhub.wallet.entity;

import com.truejacobg.currencyhub.wallet.dto.WalletCurrencyExchangeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("exchange")
@AllArgsConstructor
@Getter
@Setter
@Data
public class WalletCurrencyExchangeEntity {
    @Id
    private String name;
    private List<WalletCurrencyExchangeDTO> exchangeCurrencyDTOList;
}
