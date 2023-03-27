package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFileServiceImpl implements WorkWithFIleService {


    @Override
    public List<String> getListFromFile() {
        return null;
    }

    @Override
    public void writeToFile(String userInfo) {

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/ruslanmuhametzanov/ideaFiles/output"));
            bufferedWriter.write(userInfo);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл" + e);
            throw new RuntimeException(e);
        }
    }
}
