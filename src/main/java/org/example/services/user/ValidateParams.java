package org.example.services.user;

public interface ValidateParams {
    /**
     *  Проверяем параметр пола, допустимые входные данные (М, м, Ж, ж)
     */
    boolean validateSexParams(String sex);

    /**
     * Проверка параметров ФИО, допустимые входные данные (а-я, А-Я)
     */
    boolean validateFullNameParams(String[] fullNameArray);

    /**
     * Проверка параметров Даты рождения, допустимые входные данные (числа, проверка чисел на реальую дату по календарю)
     */
   boolean validateBirthdayParams(int[] dateArray);
}
