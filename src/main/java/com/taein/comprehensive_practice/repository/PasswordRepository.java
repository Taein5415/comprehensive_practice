package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PasswordRepository {
    private final Storage filePasswordStorage;
    private final List<String> passwordList;

    private static PasswordRepository instance;
    private PasswordRepository(){
        filePasswordStorage = FilePasswordStorage.getInstance();
        passwordList = filePasswordStorage.load();
    }

    public static PasswordRepository getInstance(){
        if (instance == null) {
            instance = new PasswordRepository();
        }
        return instance;
    }

    public Optional<String> select(String password){
        return passwordList.stream().filter(p->p.equals(password)).findFirst();
    }
}
