package com.taein.comprehensive_practice.ui;

import com.taein.comprehensive_practice.repository.DrinkRepository;
import com.taein.comprehensive_practice.repository.FileDrinkStorage;
import com.taein.comprehensive_practice.service.DrinkService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private final Scanner scanner;
    private DrinkService drinkService;

    public Application(){
        scanner = new Scanner(System.in);
        drinkService = new DrinkService(new DrinkRepository(new FileDrinkStorage()));
    }

    public void run() {
        while (true) {
            System.out.println("\n===== 자판기 프로그램 =====");
            System.out.println("1. 음료 조회");
            System.out.println("2. 음료 구매");
            System.out.println("3. 동전 삽입");
            System.out.println("4. 거스름돈 받기");
            System.out.println("5. 재고 추가");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1 -> showAllDrinks();
                    case 2 -> buyDrinks();
                    case 3 -> insertCoin();
                    //case 4 -> getChange();
                    case 5 -> addInventory();
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

    private void insertCoin() {

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
            drinkService.buyDrink(name);
            System.out.println(name+" 구매 완료했습니다.");
        }catch(Exception e){
            System.out.println("음료 구매 실패 : "+e.getMessage());
        }
    }

    private void showAllDrinks() {
        drinkService.findAllDrinks().forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
