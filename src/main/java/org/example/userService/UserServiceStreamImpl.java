package org.example.userService;

import org.example.UtilClasses.User;
import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.workWithFileService.WorkWithFIleService;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserServiceStreamImpl implements UserService {
    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;

    public UserServiceStreamImpl() {
        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
    }

    @Override
    public List<User> deleteUser(String lastName) {
        List<User> listAfterDelete = new ArrayList<>();
        List<User> allUsers = getAllUsers();
        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
        for (User user : allUsers) {
            if (user.getLastName().equals(lastName)) {
                System.out.println("Нашли совпадение по: " + lastName + "\n" + "Пропускаем  его  добавление");
                continue;
            }
            listAfterDelete.add(user);
        }
        System.out.println("Получили итоговый список без запрашиваемого пользователя: " + allUsers);
        return listAfterDelete;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> listFromFile = workWithFIleService.getListFromFile();
        List<User> users = listConverter.stringToUsers(listFromFile);
        return users;
    }

    @Override
    public User getUser(String lastNameSearch) {
        User user = null;
        List<User> allUsers = getAllUsers();
        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
        System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
        for (User elementOfUsers : allUsers) {
            System.out.println("Проходимся циклом по списку, элемент из списка = " + elementOfUsers);
            System.out.println(elementOfUsers.getLastName());
            if (elementOfUsers.getLastName().equals(lastNameSearch)) {
                user = elementOfUsers;
                System.out.println("Нашли  пользователя по фамилии: " + user);
            }
        }
        return user;
    }

    @Override
    public List<User> updateUser(String firstParam, String secondParam) {
        List<User> listAfterDelete = new ArrayList<>();
        List<User> allUsers = getAllUsers();
        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
        System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
        for (User user : allUsers) {
            if (user.getLastName().equals(firstParam)) {
                System.out.println("Нашли совпадение по: " + firstParam + "\n" + "Редактируем его");
                user.setLastName(secondParam);
            }
            listAfterDelete.add(user);
        }
        System.out.println("Получили итоговый список: " + allUsers);
        return listAfterDelete;
    }



}
