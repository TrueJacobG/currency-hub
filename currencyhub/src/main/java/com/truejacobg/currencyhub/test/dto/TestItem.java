package com.truejacobg.currencyhub.test.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("testItems")
public class TestItem {
    @Id
    private String id;

    private String name;
    private int quantity;

    public TestItem(String id, String name, int quantity) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
