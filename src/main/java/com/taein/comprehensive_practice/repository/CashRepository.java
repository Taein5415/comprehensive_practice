package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashRepository {
    private final Storage fileCashStorage;
    private final List<Cash> cashList;

    private static CashRepository instance;
    private CashRepository(){
        fileCashStorage = FileCashStorage.getInstance();
        cashList = fileCashStorage.load();
    }

    public static CashRepository getInstance(){
        if (instance == null) {
            instance = new CashRepository();
        }
        return instance;
    }

    public List<Cash> selectAllCash(){
        return new ArrayList<Cash>(cashList);
    }

    public Optional<Cash> selectByUnit(int unit){
        return cashList.stream().filter(c->c.getUnit()==unit).findFirst();
    }


    public void update(Cash cash) {
        selectByUnit(cash.getUnit()).get().setQuantity( cash.getQuantity());
        fileCashStorage.save(cashList);
    }
}
