package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.SalesHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesHistoryRepository {
    private final Storage fileSalesHistoryStorage;
    private final List<SalesHistory> historyList;

    private static SalesHistoryRepository instance;
    private SalesHistoryRepository(){
        fileSalesHistoryStorage = FileSalesHistoryStorage.getInstance();
        historyList = fileSalesHistoryStorage.load();
    }

    public static SalesHistoryRepository getInstance(){
        if (instance == null) {
            instance = new SalesHistoryRepository();
        }
        return instance;
    }

    public List<SalesHistory> selectAllHistory(){
        return new ArrayList<SalesHistory>(historyList);
    }
    public void insert(SalesHistory history){
        historyList.add(history);
        fileSalesHistoryStorage.save(historyList);
    }
}
