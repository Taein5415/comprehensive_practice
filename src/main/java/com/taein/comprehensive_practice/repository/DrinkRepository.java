package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Drink;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    private final Storage fileDrinkStorage;
    private final List<Drink> drinkList;

    public DrinkRepository(FileDrinkStorage fileDrinkStorage){
        this.fileDrinkStorage = fileDrinkStorage;
        drinkList = fileDrinkStorage.load();
    }

    public List<Drink> selectAllDrinks(){
        return new ArrayList<Drink>(drinkList);
    }


    public Drink selectByname(String name) {
        return (Drink) drinkList.stream().filter(d -> d.getName().equals(name));
    }

    public void updateDrink(Drink drink) {
        for(int i=0;i<drinkList.size();i++){
            if(drinkList.get(i).getName().equals(drink.getName())) {
                drinkList.set(i, drink);
                fileDrinkStorage.save(drinkList);
            }
        }
    }
}
