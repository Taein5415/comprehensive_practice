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
        if((drink = drinkRepository.selectByName(name))==null){
            throw new IllegalArgumentException("해당 음료는 존재하지 않습니다.");
        }
        if(drink.getQuantity() <1){
            throw new IllegalArgumentException("해당 음료의 재고가 없습니다.");
        }
        drink.setQuantity(drink.getQuantity()-1);
        drinkRepository.updateDrink(drink);

    }


    public List<Drink> findAllDrinks(){
        return drinkRepository.selectAllDrinks();
    }

    public void createAndSaveDrink(String name, int price, int quantity) {
        Drink drink = new Drink(name, price, quantity);
        drinkRepository.save(drink);
    }
}
