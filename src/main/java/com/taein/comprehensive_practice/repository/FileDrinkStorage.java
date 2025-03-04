package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Drink;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDrinkStorage implements Storage<Drink> {
    private final static String FILE_PATH = "src/main/java/com/taein/comprehensive_practice/db/drinkDB.txt";
    @Override
    public void save(List<Drink> list) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException("drinkDB.txt 파일 저장 오류 ");
        }
    }

    @Override
    public List<Drink> load() {
        File file = new File(FILE_PATH);
        if(!file.exists())
            return new ArrayList<Drink>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Drink>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("drinkDB.txt 파일 읽기 오류");
        }
    }
}
