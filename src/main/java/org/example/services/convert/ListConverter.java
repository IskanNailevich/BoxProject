package org.example.services.convert;

import org.example.utilClasses.User;

import java.util.List;

public interface ListConverter {
    /**
     * Метод преобразует строку в которой хранятся данные пользователя.
     *
     * @param list - Переданный на вход лист строк
     * @return - Лист в котором хранятся пользователи в виде отдельного объекта.
     */
    List<User> stringToUsers(List<String> list);
}
