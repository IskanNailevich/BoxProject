package org.example.startApp;


import org.example.services.user.UserServiceStreamImpl;
import org.example.services.workWithFile.WorkWithFIle;
import org.example.services.workWithFile.WorkWithFileImpl;
import org.example.utilClasses.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    static UserServiceStreamImpl userServiceStream = new UserServiceStreamImpl();
    static WorkWithFIle workWithFIleService = new WorkWithFileImpl(); //todo не используется
    private static void printCreate(){ //todo перенести в приват метод новгго сервиса внизу аналогично
        System.out.println("Create");
        userServiceStream.create();
        System.out.println("Успешно создали пользователя и записали в файл: ");
    }
    private static void printUpdate(){
        System.out.println("Update");
        userServiceStream.update();
    }
    private static void printDelete(){
        System.out.println("Delete");
        userServiceStream.delete();
    }
    private static void printGet(){
        System.out.println("get");
        System.out.println("Нашли  пользователя по айди:\n\t" + userServiceStream.get());
    }
    private static void printGetAll(){
        System.out.println("GetAll");
        for (User allUser : userServiceStream.getAllUsers()) {
            System.out.println(allUser);
        }
    }

    private static void printMistake(){
        System.out.println("Ошибка, введите от 1 до 5");
    }
    public static void main(String[] args) {
        int choice;
        String keyboard = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать!\nПожалуйста введите что вы хотите сделать:\n\t1-создать пользователя\n\t2-получить пользователя\n" +
                "\t3-обновить данные пользователя\n\t4-удалить пользователя\n\t5-получить всех пользователей");


            while (!keyboard.equals("exit")) {
                try {
                keyboard = reader.readLine();
                choice = Integer.parseInt(keyboard);
                switch (choice) {
                    case 1:
                        printCreate();
                        break;
                    case 2:
                        printGet();
                        break;
                    case 3:
                        printUpdate();
                        break;
                    case 4:
                        printDelete();
                        break;
                    case 5:
                        printGetAll();
                        break;
                    default:
                        printMistake();
                        break;
                }
                System.out.println("\n\nДля продолжения работы, наберите от 1 до 5:" +
                        "\n\t1-создать пользователя\n\t2-получить пользователя\n" +
                        "\t3-обновить данные пользователя\n\t4-удалить пользователя\n\t5-получить всех пользователей\n" +
                        "Для выхода наберите \"exit\"");

            } catch(NumberFormatException e){
                System.out.println("Пожалуйста введите число");
            } catch(IOException e){
                System.out.println("Ошибка при чтении строки с консоли" + e);
            }
        }
    }
}