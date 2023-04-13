package org.example.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateParamsImpl.class);

    @Override
    public boolean validateSexParams(String sex) {
        LOGGER.info("Начал работу метод по проверке параметра пола!");
        if (sex.equals(MALE) || sex.equals(FEMALE) ||
                sex.equals(MALE_LOWERCASE) || sex.equals(FEMALE_LOWERCASE)) {
            return true;
        } else {
            System.out.println("Ошибка, параметр sex не верный");
            LOGGER.info("Метод по проверке параметра пола закончил работу!");
            return false;
        }
    }

    @Override
    public boolean validateFullNameParams(String[] fullNameArray) {
        LOGGER.info("Начал работу метод по проверке ФИО!");
        boolean checkStringParam = false;
        for (String checkUserStringsParam : fullNameArray) {
            if (checkUserStringsParam.matches("[а-яА-Я]+")) {
                checkStringParam = true;
            } else {
                checkStringParam = false;
                break;
            }
        }
        LOGGER.info("Метод по проверке ФИО акончил работу!");
        return checkStringParam;
    }

    @Override
    public boolean validateBirthdayParams(int[] dateArray) {
        LOGGER.info("Начал работу метод по проверке даты!");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
        String tmpDataCheck = "";
        String delimeter = "-";
        for (int i = 0; i < dateArray.length; i++) {
            if (i == dateArray.length - 1) {
                delimeter = "";
            }
            tmpDataCheck += dateArray[i] + delimeter;
        }
        try {
            LocalDate date = LocalDate.parse(tmpDataCheck, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка в формате даты");
            return false;
        }
        LOGGER.info("Метод по проверке даты закончил работу!");
        return true;
    }
}
