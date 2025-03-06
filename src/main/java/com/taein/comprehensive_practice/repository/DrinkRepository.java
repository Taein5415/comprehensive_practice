package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Drink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrinkRepository {
    private final Storage fileDrinkStorage;
    private final List<Drink> drinkList;

    private static DrinkRepository instance;
    private DrinkRepository(){
        fileDrinkStorage = FileDrinkStorage.getInstance();
        drinkList = fileDrinkStorage.load();
    }

    public static DrinkRepository getInstance(){
        if (instance == null) {
            instance = new DrinkRepository();
        }
        return instance;
    }

    public List<Drink> selectAllDrinks(){
        return new ArrayList<Drink>(drinkList);
    }


    public Optional<Drink> selectByName(String name) {
        Optional<Drink> drink = drinkList.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst(); // 첫 번째 일치하는 요소 반환

        return drink;
    }

    public void insert(Drink drink){
        drinkList.add(drink);
        fileDrinkStorage.save(drinkList);
    }

    public void updateDrink(Drink drink) {
        for(int i=0;i<drinkList.size();i++){
            if(drinkList.get(i).getName().equals(drink.getName())) {
                drinkList.set(i, drink);
                fileDrinkStorage.save(drinkList);
            }
        }
    }


    public void delete(String name) {
        drinkList.removeIf(drink->drink.getName().equals(name));
        fileDrinkStorage.save(drinkList);
    }
}
