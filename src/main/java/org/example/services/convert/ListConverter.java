package org.example.services.convert;

import org.example.utilClasses.User;

import java.util.List;

/**
 * Сервис преобразования данных пользователя
 */

public interface ListConverter {

    /**
     * Преобразование данных пользователя в String
     * @param users - переданный на вход лист юзеров
     * @return - лист строк в котором каждый юзер представлен в виде строки
     */
    List<String> usersToString(List<User> users);

    /**
     * Метод преобразует строку в которой хранятся данные юзера
     * @param list - переданный на вход лист строк
     * @return - лист в котором хранятся юзеры в виде отдельного объекта
     */
    List<User> stringToUsers(List<String> list);
}
