package com.taein.comprehensive_practice.ui;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.DrinkRepository;
import com.taein.comprehensive_practice.repository.FileCashStorage;
import com.taein.comprehensive_practice.repository.FileDrinkStorage;
import com.taein.comprehensive_practice.service.CashService;
import com.taein.comprehensive_practice.service.DrinkService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserCLI {
    private final Scanner scanner;
    private DrinkService drinkService;
    private CashService cashService;

    public UserCLI(){
        scanner = new Scanner(System.in);
        drinkService = DrinkService.getInstance();
        cashService = CashService.getInstance();
    }

    public void run() {
        while (true) {
            System.out.println("\n===== 자판기 프로그램 =====");
            System.out.println("투입액 : "+cashService.getTotalAmount()+"원");
            System.out.println("1. 음료 조회");
            System.out.println("2. 음료 구매");
            System.out.println("3. 화폐 삽입");
            System.out.println("4. 거스름돈 받기");
            System.out.println("5. 재고 추가");
            System.out.println("6. 화폐 관리");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1 -> showAllDrinks();
                    case 2 -> buyDrinks();
                    case 3 -> insertCoin();
                    case 4 -> returnChange();
                    case 5 -> addInventory();
                    case 6 -> manageCash();
                    //case 999 ->
                    case 9 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void returnChange() {
        List<Cash> list = cashService.getChange();
        StringBuilder sb = new StringBuilder();
        for(Cash cash : list){
            sb.append(cash.toString());
            sb.append(" ");
        }
        sb.append("반환했습니다.");
        System.out.println(sb);

    }

    private void manageCash() {
        System.out.println("\n===== 화폐 관리 =====");
        System.out.println("1. 화폐 목록 확인");
        System.out.println("2. 화폐 추가");
        System.out.println("3. 화폐 수거");
        System.out.print("메뉴 선택: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력입니다.");
        }
        switch (choice) {
            case 1 -> printCashList();
            case 2 -> addCash();
            //case 3 -> collectCash();
            default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    private void printCashList(){
        cashService.findAllCash().forEach(System.out::println);
    }

    private void addCash() {
        try {
            System.out.println("\n===== 화폐 추가 =====");
            System.out.print("화폐 단위 : ");
            int value = scanner.nextInt();
            System.out.print("화폐 개수 : ");
            int quantity = scanner.nextInt();
            cashService.addCash(value,quantity);
            System.out.println("화폐가 추가되었습니다.");
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력압니다.");
        }
    }

    private void insertCoin() {
        System.out.println("입력 화폐단위");
        cashService.getUnitList().forEach(unit -> System.out.print(unit+" "));
        System.out.println();
        System.out.print("입력할 화폐의 단위를 입력하세요 : ");
        int unit = scanner.nextInt();
        cashService.inputCash(unit);
    }

    private void addInventory() {
        try {
            System.out.print("음료 이름 입력>>");
            String name = scanner.nextLine();
            System.out.print("가격 입력");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("재고 수 입력");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            // 음료 객체 생성 및 DB 저장
            drinkService.createAndSaveDrink(name, price, quantity);
        }catch(Exception ex) {
            throw new InputMismatchException("재고 입력에 실패했습니다.");
        }


    }

    private void buyDrinks() {
        showAllDrinks();
        System.out.println("구매하실 음료의 이름을 입력하세요: ");
        try {
            String name = scanner.nextLine();
            Drink drink = drinkService.findByName(name);
            int totalAmount = cashService.getTotalAmount();
            if(cashService.isPurchasable(drink.getPrice())){
                drinkService.purchaseDrink(name);
                cashService.reduceTotalAmount(drink.getPrice());
                System.out.println(name+" 구매 완료했습니다.");
            }
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력입니다.");
        }catch(Exception e){
            System.out.println("음료 구매 실패 : "+e.getMessage());
        }
    }

    private void showAllDrinks() {
        drinkService.findAllDrinks().forEach(System.out::println);
    }
}
