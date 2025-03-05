package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Drink;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrinkRepository {
    private final Storage fileDrinkStorage;
    private final List<Drink> drinkList;

    public void save(Drink drink){
        drinkList.add(drink);
        fileDrinkStorage.save(drinkList);
    }

    public DrinkRepository(FileDrinkStorage fileDrinkStorage){
        this.fileDrinkStorage = fileDrinkStorage;
        drinkList = fileDrinkStorage.load();
    }

    public List<Drink> selectAllDrinks(){
        return new ArrayList<Drink>(drinkList);
    }


    public Drink selectByName(String name) {
        Optional<Drink> drink = drinkList.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst(); // 첫 번째 일치하는 요소 반환

        // 값이 존재하면 출력, 없으면 메시지 출력
        if(drink.isPresent())
            return drink.get();
        else
            throw new NullPointerException("해당 음료는 존재하지 않습니다.");
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
