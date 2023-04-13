package org.example.services.SecondThread;

import org.example.services.user.UserService;
import org.example.services.user.UserServiceStreamImpl;
import org.example.utilClasses.User;

public class PrintUsersImpl extends Thread implements PrintUsers{

    UserService userService = new UserServiceStreamImpl();
    public void printInConsole() {
        System.out.println("\nСейчас в файле количество пользователй составляет: " + userService.getAllUsers().size());
        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }
        System.out.println();
    }

    @Override
    public void run() {
        while (true){
            printInConsole();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
