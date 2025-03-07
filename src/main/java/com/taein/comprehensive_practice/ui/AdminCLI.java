package com.taein.comprehensive_practice.ui;

import com.taein.comprehensive_practice.domain.SalesHistory;
import com.taein.comprehensive_practice.service.CashService;
import com.taein.comprehensive_practice.service.DrinkService;
import com.taein.comprehensive_practice.service.SalesHistoryService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminCLI {
    private final Scanner scanner;
    private DrinkService drinkService;
    private CashService cashService;
    private SalesHistoryService salesHistoryService;

    public AdminCLI(){
        scanner = new Scanner(System.in);
        drinkService = DrinkService.getInstance();
        cashService = CashService.getInstance();
        salesHistoryService = SalesHistoryService.getInstance();
    }
    public void run(){
        while (true) {
            System.out.println("\n===== 관리자 모드 =====");
            System.out.println("1. 재고 관리");
            System.out.println("2. 화폐 관리");
            System.out.println("3. 판매 내역 조회");
            System.out.println("9. 관리자 모드 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1 -> manageInventory();
                    case 2 -> manageCash();
                    case 3 -> printSalesHistory();
                    case 9 -> {
                        System.out.println("관리자 모드를 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void printSalesHistory() {
        List<SalesHistory> historyList = salesHistoryService.findAllHistory();
        for(SalesHistory h:historyList){
            System.out.println(h.toString());
        }
    }

    private void manageInventory() {
        System.out.println("\n===== 음료 관리 =====");
        System.out.println("1. 음료 목록 확인");
        System.out.println("2. 음료 추가");
        System.out.println("3. 음료 제거");
        System.out.print("메뉴 선택: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력입니다.");
        }
        switch (choice) {
            case 1 -> showAllDrinks();
            case 2 -> addInventory();
            case 3 -> removeInventory();
            default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    private void showAllDrinks() {
        drinkService.findAllDrinks().forEach(System.out::println);
    }

    private void addInventory() {
        try {
            System.out.print("음료 이름 입력>>");
            String name = scanner.nextLine();
            System.out.print("가격 입력>>");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.print("재고 수 입력>>");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            // 음료 객체 생성 및 DB 저장
            drinkService.createAndSaveDrink(name, price, quantity);
        }catch(Exception ex) {
            throw new RuntimeException("재고 입력에 실패했습니다.");
        }
    }

    private void removeInventory(){
        System.out.print("음료 이름 입력>>");
        String name = scanner.nextLine();
        drinkService.removeDrink(name);
        System.out.println("음료가 제거되었습니다.");
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
            case 3 -> collectCash();
            default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
        }
    }

    private void collectCash() {
        printCashList();
        try {
            System.out.println("\n===== 화폐 삭제 =====");
            System.out.print("화폐 단위 : ");
            int unit = scanner.nextInt();
            System.out.print("화폐 개수 : ");
            int quantity = scanner.nextInt();
            cashService.collectCash(unit,quantity);
            System.out.println(unit+"원 "+quantity+"개 수거되었습니다.");
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력압니다.");
        }
    }

    private void printCashList(){
        cashService.findAllCash().forEach(System.out::println);
    }

    private void addCash() {
        try {
            System.out.println("\n===== 화폐 추가 =====");
            System.out.print("화폐 단위 : ");
            int unit = scanner.nextInt();
            System.out.print("화폐 개수 : ");
            int quantity = scanner.nextInt();
            cashService.addCash(unit,quantity);
            System.out.println("화폐가 추가되었습니다.");
        }catch(InputMismatchException e){
            throw new InputMismatchException("잘못된 입력압니다.");
        }
    }

}
