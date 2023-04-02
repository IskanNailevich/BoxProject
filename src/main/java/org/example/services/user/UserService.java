package org.example.services.user;

import org.example.utilClasses.User;

import java.util.List;

public interface UserService {
    /**
     * читает данные с консоли
     * проверяет эти параметры
     * создает пользователя на основе проверенных параметров
     * отправляет созданного юзера в файл
     */
    void create();

    List<User> deleteUserWithReturningNewList(String string);

    List<User> getAllUsers();

    /**
     * Возвращает пользователя по id
     * @param
     * @return
     */
    User get(int id);

    void update();

    String parseUserInfoToString(User user);

    void deleteUserWithReplacingToEmptyLine();


}