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

    /**
     * Считывает с консоли айди пользователя для удаления
     * Получает лист юзеров без пользователя которого требуется удалить
     * Корректируя айди у всех пользователей, записывает в файл
     */
    void delete();

    /**
     * Возвращает список всех пользователей
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     * Возвращает пользователя по айди введеного с консоли
     *
     * @return
     */
    User get();

    /**
     * Возвращает пользователя по id
     *
     * @param
     * @return
     */
    User getById(int id);


    /**
     * Считывает с консоли айди пользователя, которого требуется обновить
     * Считывает с консоли что именно требуется сделать с юзером
     * Выполняется фукционал метода {@link org.example.services.user.UserServiceStreamImpl# updateByChoice(User, int) update}
     */
    void update();


}