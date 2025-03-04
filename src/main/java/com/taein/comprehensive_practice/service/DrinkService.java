package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.repository.DrinkRepository;

import java.util.List;

public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository){
        this.drinkRepository = drinkRepository;
    }

    public void buyDrink(String name) {
        Drink drink;
        if((drink = drinkRepository.selectByname(name))==null){
            throw new IllegalArgumentException("해당 음료는 존재하지 않습니다.");
        }
        if(drink.getQuantity() <1){
            throw new IllegalArgumentException("해당 음료의 재고가 없습니다.");
        }
        drink.setQuantity(drink.getQuantity()-1);
        drinkRepository.updateDrink(drink);

    }

    private static boolean drinkNotExists(String name) {

    }

    public List<Drink> findAllDrinks(){
        return drinkRepository.selectAllDrinks();
    }
}
