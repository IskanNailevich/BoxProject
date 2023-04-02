package org.example.services.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InitParamsImpl implements InitParams {


    public String readFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
       // System.out.println("Начало чтения данных с консоли");
        try {
            line = reader.readLine();
         //   System.out.println("Успешно считали строку с консоли: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка ввода " + e);
        }
        return line;
    }


    @Override
    public String sexParams() {
    //    System.out.println("Начало работы метода по параметру пола");
        System.out.println("Введите ваш пол (М или Ж)");
        String inputSex = null;
        inputSex = readFromConsole();
      //  System.out.println("Успешно прочитали параметр пола: " + inputSex);
        return inputSex;
    }

    @Override
    public String[] fullNameParams() {
      //  System.out.println("Начало работы метода по добавлению строк для ФИО");
        String[] userStringData = new String[3];
        for (int i = 0; i < 3; ) {
            String choice = null;
            if (i == 0) {
                choice = "фамилию";
            } else if (i == 1) {
                choice = "имя";
            } else {
                choice = "отчество";
            }
            System.out.printf("Введите %s\n", choice);
            userStringData[i] = readFromConsole();
            if (userStringData[i].matches("[а-яА-Я]+")) {
             //   System.out.println("Успешно считали строку: " + userStringData[i]);
                i++;
            } else {
                System.out.println("Ошибка, введите данные используя русский алфавит от А до Я");
            }
        }
       // System.out.println("Успешно собрали строки для ФИО, возвращаем массив строк");
        return userStringData;

    }

    @Override
    public int[] birthdayParams() {
        //System.out.println("Начало работы метода по добавлению чисел, для дальнейшего даты рождения");
        int[] userBirthdayParams = new int[3];
        for (int i = 0; i < 3; ) {
            String choice = null;
            if (i == 0) {
                choice = "год рождения";
            } else if (i == 1) {
                choice = "месяц";
            } else {
                choice = "день";
            }
            System.out.printf("Введите %s\n", choice);
            try {
                userBirthdayParams[i] = Integer.parseInt(readFromConsole());
           //     System.out.println("Успешно считали число: " + userBirthdayParams[i]);
                i++;
            } catch (Exception e) {
                System.out.println("ошибка, вы ввели неправильное число");
            }
        }
        //System.out.println("Успешно собрали числа для даты рождения, возвращаем массив чисел");
        return userBirthdayParams;

    }
}
