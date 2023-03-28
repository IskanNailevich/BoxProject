package org.example.workWithFileService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithFileServiceImpl implements WorkWithFIleService {

    public static final String FILE_NAME = "/Users/ruslanmuhametzanov/ideaFiles/output";

    @Override
    public List<String> getListFromFile() {
        List<String> resultList;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
            String result = bufferedReader.readLine();
            System.out.println("Строка после вычитки: " + result);
            String[] splitParam = result.split(";", 0);
            System.out.println("Печатаем каждого юзера: ");
            for (int i = 0; i < splitParam.length; i++) {
                String s = splitParam[i];
                System.out.println("Элемент массива = " + s);
            }
            resultList = Arrays.stream(splitParam).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void writeUserDataToFile(String userInfo) {
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
