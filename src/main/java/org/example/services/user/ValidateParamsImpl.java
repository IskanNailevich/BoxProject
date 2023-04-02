package org.example.services.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class ValidateParamsImpl implements ValidateParams {
    /**
     * Constants
     */
    private static final String MALE = "М";
    private static final String MALE_LOWERCASE = "м";
    private static final String FEMALE = "Ж";
    private static final String FEMALE_LOWERCASE = "ж";
    private final String DATE_PATTERN = "yyyy-M-d";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);


    @Override
    public boolean validateSexParams(String sex) {
        System.out.println("Начало проверки параметра пола(sex)");
        if (sex.equals(MALE) || sex.equals(FEMALE) ||
                sex.equals(MALE_LOWERCASE) || sex.equals(FEMALE_LOWERCASE)) {
            System.out.println("Успешно проверили параметр пола");
            return true;
        } else {
            System.out.println("Ошибка, параметр не верный");
            return false;
        }

    }

    @Override
    public boolean validateFullNameParams(String[] fullNameArray) {
        System.out.println("Начало проверки на отсутствие в строке иных символов кроме букв");
        boolean checkStringParam = false;
        for (String checkUserStringsParam : fullNameArray) {
            if (checkUserStringsParam.matches("[а-яА-Я]+")) {
                checkStringParam = true;
            } else {
                checkStringParam = false;
                break;
            }
        }
        return checkStringParam;

    }

    @Override
    public boolean validateBirthdayParams(int[] dateArray) {
        System.out.println("Начало проверки массива чисел");
        System.out.println("проверяем массива чисел даты рождения на соотвтествие с реальными датами");
        String tmpDataCheck = "";
        String delimeter = "-";
        for (int i = 0; i < dateArray.length; i++) {
            if (i == dateArray.length - 1) {
                delimeter = "";
            }
            tmpDataCheck += dateArray[i] + delimeter;
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
}
