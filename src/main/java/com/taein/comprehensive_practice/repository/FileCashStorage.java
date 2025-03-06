package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;
import com.taein.comprehensive_practice.domain.Drink;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCashStorage implements Storage<Cash> {
    private final static String FILE_PATH = "src/main/java/com/taein/comprehensive_practice/db/cashDB.dat";

    private static FileCashStorage instance;
    private FileCashStorage(){}

    public static FileCashStorage getInstance(){
        if (instance == null) {
            instance = new FileCashStorage();
        }
        return instance;
    }

    @Override
    public void save(List<Cash> list) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException("cashDB.txt 파일 저장 오류 ");
        }
    }


    @Override
    public List<Cash> load() {
        File file = new File(FILE_PATH);
        if(!file.exists())
            return new ArrayList<Cash>(List.of(
                    new Cash(50,0),
                    new Cash(100,0),
                    new Cash(500,0),
                    new Cash(1000,0),
                    new Cash(5000,0)
            ));
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Cash>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("cashDB.txt 파일 읽기 오류");
        }
    }
}
