package org.example.services.SecondThread;

import org.example.services.user.UserService;
import org.example.services.user.UserServiceStreamImpl;
import org.example.utilClasses.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintUsersImpl extends Thread implements PrintUsers {

    UserService userService = new UserServiceStreamImpl();

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintUsersImpl.class);

    public void printInConsole() {
        LOGGER.info("Начал работу метод по распечатке всех пользователйей!");
        System.out.println("\nСейчас в файле количество пользователй составляет: " + userService.getAllUsers().size());
        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }
        System.out.println();
        LOGGER.info("Метод по распечатке закончил работу!");
    }

    @Override
    public void run() {
        LOGGER.info("Начал работу метод по выводу всех пользователй в консоль!");
        while (true) {
            printInConsole();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("Метод по выводу закончил работу!");
        }
    }
}
