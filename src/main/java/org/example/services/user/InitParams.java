package org.example.services.user;

public interface InitParams {
    /**
     * Чтение строки с консоли.
     *
     * @return Прочитанная строка с консоли.
     */
    String readFromConsole();

    /**
     * Чтение пола пользователя с консоли.
     *
     * @return Пол пользователя.
     */
    String sexParams();

    /**
     * Чтение ФИО с консоли.
     *
     * @return Массив с информацией о ФИО пользователя.
     */
    String[] fullNameParams();

    /**
     * Чтение даты рождения с консоли.
     *
     * @return Массив с информацией о дате рождения пользователя.
     */
    int[] birthdayParams();

}
