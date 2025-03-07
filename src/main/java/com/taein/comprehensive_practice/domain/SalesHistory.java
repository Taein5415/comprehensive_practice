package com.taein.comprehensive_practice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesHistory implements Serializable {
    String drinkName;
    int price;
    LocalDateTime salesDateTime;

    public SalesHistory(String drinkName, int price, LocalDateTime salesDateTime) {
        this.drinkName = drinkName;
        this.price = price;
        this.salesDateTime = salesDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("(yyyy.MM.dd HH:mm)");
        // 포맷 적용
        String formattedDate = this.salesDateTime.format(formatter);
        return formattedDate+" "+drinkName+" "+price+"원";
    }
}
