package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithFileServiceImpl implements WorkWithFIleService {

    public  static final String FILE_NAME = "/Users/ruslanmuhametzanov/ideaFiles/output";


    @Override
    public List<String> getListFromFile() {

        List<String> resultList;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String result = bufferedReader.readLine();
            String[] splitParam = result.split(";");
            resultList = Arrays.stream(splitParam).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void writeToFile(String userInfo) {

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            bufferedWriter.write(userInfo);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл" + e);
            throw new RuntimeException(e);
        }
    }
}
