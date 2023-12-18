package com.truejacobg.currencyhub.wallet.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("wallet")
@AllArgsConstructor
@Getter
@Setter
@Data
public class WalletEntity {
    @Id
    private String name;
    private Map<String, Double> walletMap;
}
