package org.example.startApp;


import org.example.services.user.CreateUserService;
import org.example.services.user.CreateUserServiceImpl;
import org.example.services.user.InitParamsImpl;
import org.example.services.user.UserServiceStreamImpl;
import org.example.services.workWithFileService.WorkWithFIleService;
import org.example.services.workWithFileService.WorkWithFileServiceImpl;
import org.example.utilClasses.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    static UserServiceStreamImpl userServiceStream = new UserServiceStreamImpl();
    static WorkWithFIleService workWithFIleService = new WorkWithFileServiceImpl();
    private static void printCreate(){
        System.out.println("Create");

        userServiceStream.create();
    }
    private static void printUpdate(){
        System.out.println("Update");
    }
    private static void printDelete(){
        System.out.println("Delete");
    }
    private static void printGet(){
        System.out.println("get");
        User u = userServiceStream.get(0);
        System.out.println(u);
    }
    private static void printGetAll(){
        System.out.println("GetAll");
        System.out.println(userServiceStream.getAllUsers());
    }
    public static void main(String[] args) {
        int choice;
        String keyboard;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать!\nПожалуйста введите что вы хотите сделать:\n\t1-создать пользователя\n\t2-получить пользователя\n" +
                "\t3-обновить данные пользователя\n\t4-удалить пользователя\n\t5-получить всех пользователей");
        try {
            keyboard = reader.readLine();
            //while(!keyboard.equals("exit")){
                choice=Integer.parseInt(keyboard);
                 switch (choice){
                    case 1 :
                        printCreate(); break;
                    case 2:
                        printGet(); break;
                    case 3:
                        printUpdate(); break;
                    case 4:
                        printDelete(); break;
                    case 5:
                        printGetAll(); break;
                    default:
                        String mistake = "Ошибка, введите от 1 до 5";
               // }
                System.out.println("Для повторного выбора наберите от 1 до 5 \nДля выхода из программы наберите exit");
            }
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста введите число");
        } catch (IOException e){
            System.out.println("Ошибка при чтении строки с консоли" + e);
        }
    }
}