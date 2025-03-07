package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.FileDrinkStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class CashService {
    private final CashRepository cashRepository;
    private static final List<Integer> unitList = List.of(50,100,500,1000,5000);
    private int totalAmount;

    private static CashService instance;
    private CashService(){
        cashRepository = CashRepository.getInstance();
    }

    public static CashService getInstance(){
        if (instance == null) {
            instance = new CashService();
        }
        return instance;
    }

    public List<Integer> getUnitList(){
        return unitList;
    }


    public List<Cash> findAllCash(){
        return cashRepository.selectAllCash();
    }

    public void addCash(int unit, int quantity) {
        if(unitList.contains(unit)){
            Cash cash = cashRepository.selectByUnit(unit).get();
            cash.add(quantity);
            cashRepository.update(cash);
        }else{
            throw new InputMismatchException("잘못된 화폐를 입력했습니다.");
        }
    }

    public void inputCash(int unit){
        addCash(unit,1);
        totalAmount+=unit;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public boolean isPurchasable(int price) {
        if(price>totalAmount)
            throw new InputMismatchException("투입금이 부족합니다.");
        return true;

    }

    public void reduceTotalAmount(int price) {
        totalAmount-=price;
    }

    public List<Cash> getChange() {
        List<Cash> list = greedy();
        totalAmount = 0;
        return list;
    }

    private List<Cash> greedy() {
        int amount = totalAmount; // 투입된 총 금액
        List<Cash> availableCashList = cashRepository.selectAllCash()
                .stream()
                .sorted((c1, c2) -> c2.getUnit() - c1.getUnit()) // 금액이 큰 순서대로 정렬
                .toList();

        List<Cash> changeList = new ArrayList<>(); // 반환할 거스름돈 리스트

        for (Cash cash : availableCashList) {
            if (amount == 0) break; // 거슬러 줄 필요가 없으면 종료

            int unit = cash.getUnit();
            int quantity = cash.getQuantity(); // 현재 자판기 내 해당 화폐 개수

            int count = Math.min(amount / unit, quantity); // 최대한 많은 화폐 사용
            if (count > 0) {
                changeList.add(new Cash(unit, count)); // 거스름돈에 추가
                amount -= count * unit; // 남은 금액 차감
            }
        }

        if (amount > 0) {
            throw new RuntimeException("거슬러 줄 수 없습니다.");
        }
        return changeList; // 거스름돈 리스트 반환
    }

    public void collectCash(int unit, int quantity) {
        if(unitList.contains(unit)){
            Cash cash = cashRepository.selectByUnit(unit).get();
            if(cash.getQuantity()<quantity)
                throw new InputMismatchException("회수할 화폐의 수가 부족합니다.");
            cash.setQuantity(cash.getQuantity()-quantity);
            cashRepository.update(cash);
        }else{
            throw new InputMismatchException("잘못된 화폐를 입력했습니다.");
        }
    }
}
