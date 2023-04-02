package org.example.services.user;

/**
 * Получение параметров пользователя с консоли
 */
public interface InitParams {
    /**
     * Чтение строки с консоли
     */
    String readFromConsole();

    /**
     * Получение параметра пола
     */
    String sexParams();

    /**
     * Получение параметров ФИО
     */
    String[] fullNameParams();

    /**
     * Получение параметров Даты рождения
     */
    int[] birthdayParams();

}
