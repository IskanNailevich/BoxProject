package org.example.services.printInfo;

import org.example.services.SecondThread.PrintUsers;
import org.example.services.SecondThread.PrintUsersImpl;
import org.example.services.user.UserService;
import org.example.services.user.UserServiceStreamImpl;
import org.example.utilClasses.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class PrintInfo {
    private UserService userServiceStream = new UserServiceStreamImpl();
    private PrintUsersImpl printUsers = new PrintUsersImpl();

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintInfo.class);

    public void print() {
        LOGGER.info("Начал работу метод по распечатке пользователей!");
        printUsers.start();
        LOGGER.info("Метод по распечатке пользователей закончил работу!");
    }

    /**
     * Метод для старта всего функционала.
     */
    public void start() {
        LOGGER.info("Начал работу метод по отображению информации для работы с пользователями!");
        int choice = 0;
        String keyboard = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать!\nПожалуйста введите что вы хотите сделать:\n\t1-создать пользователя\n\t2-получить пользователя\n" +
                "\t3-обновить данные пользователя\n\t4-удалить пользователя\n\t5-получить всех пользователей");
        while (!keyboard.equals("exit")) {
            try {
                keyboard = reader.readLine();
                if (!keyboard.equals("exit")) {
                    choice = Integer.parseInt(keyboard);
                    switch (choice) {
                        case 1: {
                            userServiceStream.create();
                            System.out.println("Успешно создали пользователя и записали в файл: ");
                        }
                        break;
                        case 2:
                            System.out.println("Нашли  пользователя по айди:\n\t" + userServiceStream.get());
                            break;
                        case 3:
                            userServiceStream.update();
                            break;
                        case 4:
                            userServiceStream.delete();
                            break;
                        case 5: {
                            for (User allUser : userServiceStream.getAllUsers()) {
                                System.out.println(allUser);
                            }
                        }
                        break;
                        default:
                            System.out.println("Ошибка, введите от 1 до 5");
                            break;
                    }
                    System.out.println("\n\nДля продолжения работы, наберите от 1 до 5:" +
                            "\n\t1-создать пользователя\n\t2-получить пользователя\n" +
                            "\t3-обновить данные пользователя\n\t4-удалить пользователя\n\t5-получить всех пользователей\n" +
                            "Для выхода наберите \"exit\"");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста введите число");
            } catch (IOException e) {
                System.out.println("Ошибка при чтении строки с консоли" + e);

            }
            LOGGER.info("Метод по работе с пользователями окончил работу!");
        }
    }
}