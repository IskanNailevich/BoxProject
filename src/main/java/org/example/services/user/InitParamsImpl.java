package org.example.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InitParamsImpl implements InitParams {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitParamsImpl.class);

    @Override
    public String readFromConsole() {
        LOGGER.info("Начал работу метод по чтению строки с консоли");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода " + e);
        }
        LOGGER.info("Метод по чтению с консоли закончил работу!");
        return line;
    }

    @Override
    public String sexParams() {
        LOGGER.info("Начал работу метод по чтению пола с консоли");
        System.out.println("Введите ваш пол (М или Ж)");
        String inputSex;
        inputSex = readFromConsole();
        LOGGER.info("Метод по чтению пола с консоли закончил работу!");
        return inputSex;
    }

    @Override
    public String[] fullNameParams() {
        LOGGER.info("Начал работу метод по чтению ФИО с консоли");
        String[] userStringData = new String[3];
        for (int i = 0; i < 3; ) {
            String choice;
            switch (i) {
                case 0:
                    choice = "фамилию";
                    break;
                case 1:
                    choice = "имя";
                    break;
                default:
                    choice = "отчество";
                    break;
            }
            System.out.printf("Введите %s\n", choice);
            userStringData[i] = readFromConsole();
            if (userStringData[i].matches("[а-яА-Я]+")) {
                i++;
            } else {
                System.out.println("Ошибка, введите данные используя русский алфавит от А до Я");
            }
        }
        LOGGER.info("Метод по чтению ФИО с консоли закончил работу!");
        return userStringData;
    }

    @Override
    public int[] birthdayParams() {
        LOGGER.info("Начал работу метод по чтению даты с консоли");
        int[] userBirthdayParams = new int[3];
        for (int i = 0; i < 3; ) {
            String choice;
            switch (i) {
                case 0:
                    choice = "год рождения";
                    break;
                case 1:
                    choice = "месяц";
                    break;
                default:
                    choice = "день";
                    break;
            }
            System.out.printf("Введите %s\n", choice);
            try {
                userBirthdayParams[i] = Integer.parseInt(readFromConsole());
                i++;
            } catch (Exception e) {
                System.out.println("Ошибка, вы ввели неправильное число");
            }
        }
        LOGGER.info("Метод по чтению даты с консоли закончил работу!");
        return userBirthdayParams;
    }
}


