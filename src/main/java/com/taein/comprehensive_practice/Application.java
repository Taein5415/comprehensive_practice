package com.taein.comprehensive_practice;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.Drink;
import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.DrinkRepository;
import com.taein.comprehensive_practice.repository.FileCashStorage;
import com.taein.comprehensive_practice.repository.FileDrinkStorage;
import com.taein.comprehensive_practice.service.CashService;
import com.taein.comprehensive_practice.service.DrinkService;
import com.taein.comprehensive_practice.ui.UserCLI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new UserCLI().run();
    }
}
