package com.taein.comprehensive_practice.repository;

import com.taein.comprehensive_practice.domain.Cash;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePasswordStorage implements Storage<String> {
    private final static String FILE_PATH = "src/main/java/com/taein/comprehensive_practice/db/admin_passwordDB.dat";
    private static FilePasswordStorage instance;
    private FilePasswordStorage(){}

    public static FilePasswordStorage getInstance(){
        if (instance == null) {
            instance = new FilePasswordStorage();
        }
        return instance;
    }

    @Override
    public void save(List<String> list) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            list.forEach(s-> {
                try {
                    bw.write(s);
                } catch (IOException e) {
                    throw new RuntimeException("admin_passwordDB.dat 파일 저장 오류");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("admin_passwordDB.dat 파일 저장 오류 ");
        }
    }

    @Override
    public List<String> load() {
        File file = new File(FILE_PATH);
        List<String> list = new ArrayList<>();
        if(!file.exists())
            return new ArrayList<String>(List.of("1234"));
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String temp;

            while((temp = br.readLine()) != null) {
                list.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException("admin_passwordDB.dat 파일 읽기 오류");
        }
        return list;
    }
}
