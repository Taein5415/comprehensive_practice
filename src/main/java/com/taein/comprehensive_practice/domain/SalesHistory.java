package com.taein.comprehensive_practice.domain;

import java.time.LocalDateTime;

public class SalesHistory {
    String drinkName;
    int price;
    LocalDateTime salesDateTime;

    public SalesHistory(String drinkName, int price, LocalDateTime salesDateTime) {
        this.drinkName = drinkName;
        this.price = price;
        this.salesDateTime = salesDateTime;
    }

}
