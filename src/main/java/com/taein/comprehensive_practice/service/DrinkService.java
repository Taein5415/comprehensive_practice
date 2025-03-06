package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.DrinkRepository;

import java.util.List;

public class DrinkService {
    private final DrinkRepository drinkRepository;

    private static DrinkService instance;
    private DrinkService(){
        drinkRepository = DrinkRepository.getInstance();
    }

    public static DrinkService getInstance(){
        if (instance == null) {
            instance = new DrinkService();
        }
        return instance;
    }

    public void purchaseDrink(String name) {
        Drink drink = findByName(name);
        drink.setQuantity(drink.getQuantity()-1);
        drinkRepository.updateDrink(drink);

    }


    public List<Drink> findAllDrinks(){
        return drinkRepository.selectAllDrinks();
    }

    public void createAndSaveDrink(String name, int price, int quantity) {
        Drink drink = new Drink(name, price, quantity);
        drinkRepository.insert(drink);
    }

    public Drink findByName(String name) {
        Drink drink;
        if((drink = drinkRepository.selectByName(name))==null){
            throw new IllegalArgumentException("해당 음료는 존재하지 않습니다.");
        }
        if(drink.getQuantity() <1){
            throw new IllegalArgumentException("해당 음료의 재고가 없습니다.");
        }
        return drink;
    }
}
