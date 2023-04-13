package org.example.services.workWithFile;

import org.example.utilClasses.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithFileImpl implements WorkWithFIle {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkWithFileImpl.class);

    private static final String FILE_NAME = "/Users/ruslanmuhametzanov/ideaFiles/output";

    @Override
    public List<String> getListFromFile() {
        LOGGER.info("Начал работу метод по получению листа пользователей из файла!");
        List<String> resultList;
        String result = readFromFile(FILE_NAME);
        String[] splitParam = result.split(";", 0);
        resultList = Arrays.stream(splitParam).collect(Collectors.toList());
        LOGGER.info("Метод по получению листа пользователей из файла закончил рааботу!");
        return resultList;
    }

    @Override
    public void writeUserDataToFile(User user, boolean isRewrite) {
        LOGGER.info("Начал работу метод по записи переданного пользователя в файл!");
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, isRewrite));
            bufferedWriter.write(String.valueOf(user));
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл" + e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Метод по записи переданного пользователя в файл закончил работу!");
    }

    /**
     * Метод вызвращает данные из файла.
     *
     * @param path путь к файлу.
     * @return данные из файла.
     */
    private String readFromFile(String path) {
        LOGGER.info("Начал работу метод по считыванию данных из файла!");
        String resultRead = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            resultRead = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка при прочтении с файла " + e);
        }
        if (resultRead == null) {
            System.out.println("\t\t\tФайл пуст при первичном считывании\n\n");
            resultRead = "";
        }
        LOGGER.info("Метод по считыванию данных из файла закончил работу!");
        return resultRead;
    }
}
