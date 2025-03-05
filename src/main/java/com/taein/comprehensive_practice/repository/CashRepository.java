package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.Drink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashRepository {
    private final Storage fileCashStorage;
    private final List<Cash> cashList;

    public void save(Cash cash){
        cashList.add(cash);
        fileCashStorage.save(cashList);
    }

    public CashRepository(Storage fileCashStorage){
        this.fileCashStorage = fileCashStorage;
        cashList = fileCashStorage.load();
    }

    public List<Cash> selectAllCash(){
        return new ArrayList<Cash>(cashList);
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
