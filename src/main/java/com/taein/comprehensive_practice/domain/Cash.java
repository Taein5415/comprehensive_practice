package com.taein.comprehensive_practice.domain;

import java.io.Serializable;

public class Cash implements Serializable {
    private int unit;
    private int quantity;

    public Cash(int unit, int quantity) {
        this.unit = unit;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return unit +"원 : "+quantity+"개";
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void add(int num){
        this.quantity+=num;
    }
    public void minus(int num){
        this.quantity-=num;
    }
}
