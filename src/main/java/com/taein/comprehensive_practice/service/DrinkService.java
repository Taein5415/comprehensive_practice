package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.repository.DrinkRepository;

import java.util.List;

public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository){
        this.drinkRepository = drinkRepository;
    }

    public static void buyDrink(String name) {
        
    }

    public List<Drink> findAllDrinks(){
        return drinkRepository.selectAllDrinks();
    }
}
