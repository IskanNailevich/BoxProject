package org.example.createUserService;

import org.example.UtilClasses.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CreateUserServiceImpl implements CreateUserService {
    public final String DATE_PATTERN = "yyyy-M-d";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
    LocalDate localDate;
    public String readFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        System.out.println("Начало чтения данных с консоли");
        try {
            line = reader.readLine();
            System.out.println("Успешно считали строку с консоли: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка ввода " + e);
        }
        return line;
    }

    public String interUserSexParam(){
        String inputSex = null;
        inputSex = readFromConsole();
        return inputSex;
    }
    public String[] interUserStringsParams() {
        System.out.println("Начало работы метода по добавлению строк для ФИО");
        String[] userStringData = new String[3];
        for (int i = 0; i < 3;) {
            String choice = null;

            if(i == 0){
                choice = "фамилию";
            }else if(i == 1){
                choice = "имя";
            }else{
                choice = "отчество";
            }

            System.out.printf("Введите %s\n", choice);
            userStringData[i] = readFromConsole();
            if(userStringData[i].matches("[а-яА-Я]+")) {
                System.out.println("Успешно считали строку: " + userStringData[i]);
                i++;
            }else{
                System.out.println("Ошибка, введите данные используя русский алфавит от А до Я");
            }
        }
        System.out.println("Успешно собрали строки для ФИО, возвращаем массив строк");
        return userStringData;
    }

    public int[] interUserBirthdayParams() {
        System.out.println("Начало работы метода по добавлению чисел, для дальнейшего даты рождения");
        int[] userBirthdayParams = new int[3];
        for (int i = 0; i < 3; ) {

            String choice = null;
            if(i == 0){
                choice = "год рождения";
            }else if(i == 1){
                choice = "месяц";
            }else{
                choice = "день";
            }

            System.out.printf("Введите %s\n", choice);

            try {
                userBirthdayParams[i] = Integer.parseInt(readFromConsole());
                System.out.println("Успешно считали число: " + userBirthdayParams[i]);
                i++;
            } catch (Exception e) {
                System.out.println("ошибка, вы ввели неправильное число");
            }
        }
        System.out.println("Успешно собрали числа для даты рождения, возвращаем массив чисел");
        return userBirthdayParams;
    }
    public boolean checkUserStringsParams(String[] checkUserStringsParams){
        System.out.println("Начало проверки на отсутствие в строке иных символов кроме букв");
        boolean checkStringParam = false;
        for (String checkUserStringsParam : checkUserStringsParams) {
            if(checkUserStringsParam.matches("[а-яА-Я]+")){
                checkStringParam = true;
            }else {
                checkStringParam = false;
                break;
            }

        }
        return checkStringParam;
    }



    public boolean checkUserBirthdayParams(int[] checkUserBirthdayParams){
        System.out.println("Начало проверки");
        System.out.println("проверяем массива чисел даты рождения на соотвтествие с реальными датами");
        String tmpDataCheck = "";
        String delimeter = "-";
        for (int i = 0; i < checkUserBirthdayParams.length; i++) {
            if(i == checkUserBirthdayParams.length-1){
                delimeter = "";
            }
            tmpDataCheck+=checkUserBirthdayParams[i] + delimeter;
        }
        System.out.println("Наша дата в строковом формате выглядит: " + tmpDataCheck);
            try {

                LocalDate date = LocalDate.parse(tmpDataCheck, dateTimeFormatter);
                System.out.println("Успешно проверили входящие параметры на соответствие с датой. Наша дата: " + date);

            } catch (DateTimeParseException e) {
                System.out.println("Ошибка в формате даты");
                return false;
            }
        System.out.println("конец проверки даты");
            return true;


    }

    
    @Override
    public User createUser() {
        User createdUser = null;
        String firstname;
        String lastname;
        String patronymic;
        int year = 1900;
        int month = 1;
        int day = 1;
        LocalDate birthday = LocalDate.of(year, month, day);
        String sex = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите имя пользователя: ");
            firstname = scanner.nextLine();
            System.out.println("Введите фамилию пользователя: ");
            lastname = scanner.nextLine();
            System.out.println("Введите отчество пользователя: ");
            patronymic = scanner.nextLine();
            System.out.println("Введите год рождения пользователя(в числовом формате): ");
            while (scanner.hasNext()) {
                year = scanner.nextInt();
                if (year < 1900 || year > 2023) {
                    System.out.println("Введите реальный год рождения (от 1900 до 2023)");
                }
                break;
            }
            System.out.println("Введите месяц рождения (в числовом формате) : ");
            while (scanner.hasNext()) {
                month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Введите реальный месяц (от 1 до 12)");
                }
                break;
            }
            System.out.println("Введите день рождения:");
            while (scanner.hasNext()) {
                day = scanner.nextInt();
                if (day < 1 || day > 31) {
                    System.out.println("Введите реальный месяц (от 1 до 31)");
                }
                break;
            }
            System.out.println("Введите пол пользователя (в формате: М, Ж)");
            while (scanner.hasNext()) {
                sex = scanner.nextLine();
                if (!sex.equals("M") && !sex.equals("Ж")) {
                    System.out.println("Введите верное значение пола: М или Ж");
                }
                break;
            }
            createdUser = new User(firstname, lastname, patronymic, birthday, sex);
            System.out.println(createdUser);
        } catch (Exception e) {
            System.out.println("Ошибка при считывании с консоли при создании пользователя");
        }
        return createdUser;
    }

    @Override
    public List<User> createAllUsers() {
        List<User> users = null;
        return users;
    }
}
