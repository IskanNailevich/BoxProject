package org.example.services.SecondThread;

public interface PrintUsers {

    /**
     * Метод во втором потоке каждые 15 секунд выводит
     * в консоль список всех пользователей
     */
    void printInConsole();

}
