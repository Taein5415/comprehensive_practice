package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Drink;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    private final FileDrinkStorage fileDrinkStorage;
    private final List<Drink> drinkList;

    public DrinkRepository(FileDrinkStorage fileDrinkStorage){
        this.fileDrinkStorage = fileDrinkStorage;
        drinkList = fileDrinkStorage.load();
    }

    public List<Drink> selectAllDrinks(){
        return new ArrayList<Drink>(drinkList);
    }


}
