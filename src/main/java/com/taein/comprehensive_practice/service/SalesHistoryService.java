package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.domain.SalesHistory;
import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.SalesHistoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class SalesHistoryService {
    private final SalesHistoryRepository salesHistoryRepository;

    private static SalesHistoryService instance;
    private SalesHistoryService(){
        salesHistoryRepository = SalesHistoryRepository.getInstance();
    }

    public static SalesHistoryService getInstance(){
        if (instance == null) {
            instance = new SalesHistoryService();
        }
        return instance;
    }

    public List<SalesHistory> findAllHistory(){
        return salesHistoryRepository.selectAllHistory();
    }

    public void addHistory(Drink drink) {
       SalesHistory salesHistory = new SalesHistory(drink.getName(), drink.getPrice(), LocalDateTime.now());
       salesHistoryRepository.insert(salesHistory);
    }
}
