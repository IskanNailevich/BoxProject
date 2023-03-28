package org.example.createUserService;

import org.example.UtilClasses.User;
import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.workWithFileService.WorkWithFIleService;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CreateUserServiceImpl implements CreateUserService {

    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;

    public CreateUserServiceImpl() {
        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
    }
    //CONSTANTS
    private static final String MALE = "М";
    private static final String MALE_LOWERCASE = "м";
    private static final String FEMALE = "Ж";
    private static final String FEMALE_LOWERCASE = "ж";
    public final String DATE_PATTERN = "yyyy-M-d";

    //DATE INFO
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

    public String interUserSexParam() {
        System.out.println("Начало работы метода по параметру пола");
        System.out.println("Введите ваш пол (М или Ж)");
        String inputSex = null;
        inputSex = readFromConsole();
        System.out.println("Успешно прочитали параметр пола: " + inputSex);
        return inputSex;
    }

    public String[] interUserStringsParams() {
        System.out.println("Начало работы метода по добавлению строк для ФИО");
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
                System.out.println("Успешно считали строку: " + userStringData[i]);
                i++;
            } else {
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
                System.out.println("Успешно считали число: " + userBirthdayParams[i]);
                i++;
            } catch (Exception e) {
                System.out.println("ошибка, вы ввели неправильное число");
            }
        }
        System.out.println("Успешно собрали числа для даты рождения, возвращаем массив чисел");
        return userBirthdayParams;
    }

    public boolean checkUserStringsParams(String[] checkUserStringsParams) {
        System.out.println("Начало проверки на отсутствие в строке иных символов кроме букв");
        boolean checkStringParam = false;
        for (String checkUserStringsParam : checkUserStringsParams) {
            if (checkUserStringsParam.matches("[а-яА-Я]+")) {
                checkStringParam = true;
            } else {
                checkStringParam = false;
                break;
            }
        }
        return checkStringParam;
    }

    public boolean checkUserBirthdayParams(int[] checkUserBirthdayParams) {
        System.out.println("Начало проверки массива чисел");
        System.out.println("проверяем массива чисел даты рождения на соотвтествие с реальными датами");
        String tmpDataCheck = "";
        String delimeter = "-";
        for (int i = 0; i < checkUserBirthdayParams.length; i++) {
            if (i == checkUserBirthdayParams.length - 1) {
                delimeter = "";
            }
            tmpDataCheck += checkUserBirthdayParams[i] + delimeter;
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

    public boolean checkUserSexParam(String checkUserSexParam) {
        System.out.println("Начало проверки параметра пола(sex)");
        if (checkUserSexParam.equals(MALE) || checkUserSexParam.equals(FEMALE) ||
                checkUserSexParam.equals(MALE_LOWERCASE) || checkUserSexParam.equals(FEMALE_LOWERCASE)) {
            System.out.println("Успешно проверили параметр пола");
            return true;
        } else {
            System.out.println("Ошибка, параметр не верный");
            return false;
        }
    }

    public User createUser() {
        String[] stringsUserInfo = interUserStringsParams();
        int[] birthdayUserInfo = interUserBirthdayParams();
        String sexUserInfo = interUserSexParam();
        while (!checkUserStringsParams(stringsUserInfo)) {
            System.out.println("Ошибка в ФИО параметрах при создании пользователя");
            stringsUserInfo = interUserStringsParams();
        }
        while (!checkUserBirthdayParams(birthdayUserInfo)) {
            System.out.println("Ошибка в дате рождения при создании пользователя");
            birthdayUserInfo = interUserBirthdayParams();
        }
        while (!checkUserSexParam(sexUserInfo)) {
            System.out.println("Ошибка в параметре пола при создании пользователя");
            sexUserInfo = interUserSexParam();
        }
        System.out.println("Все параметры успешно прошли проверку, создаем пользователя");
        User createdUser = new User(stringsUserInfo[0], stringsUserInfo[1], stringsUserInfo[2],
                LocalDate.of(birthdayUserInfo[0], birthdayUserInfo[1], birthdayUserInfo[2]), sexUserInfo);
        return createdUser;
    }

    @Override
    public void pushCreatedUsersToFile() {

        List<User> myUsersList = new ArrayList<>();
        myUsersList.add(new User("Iskander",
                "Zagidullin",
                "Nailevich",
                LocalDate.of(1994, 6, 1),
                "M"));
        myUsersList.add(new User("Ruslan",
                "Mukametzyanov",
                "Albertovich",
                LocalDate.of(1991, 12, 5),
                "M"));
        myUsersList.add(new User("Dzhamil",
                "Rakhimov",
                "Ilshatovich",
                LocalDate.of(1992, 7, 5),
                "M"));
        List<String> usersToString = myUsersList.stream().map(User::toString).collect(Collectors.toList());
        System.out.println("Print list usersToString");
        System.out.println(usersToString);
        workWithFIleService.writeUserDataToFile(usersToString.toString());

    }
}
