package org.example.userService;

import org.example.UtilClasses.User;
import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.workWithFileService.WorkWithFIleService;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceStreamImpl implements UserService {
    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;

    public UserServiceStreamImpl() {
        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
    }

    @Override
    public List<User> deleteUserWithReturningNewList(String lastName) {
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
    public User getUserByLastName(String lastNameSearch) {
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

    public void update() {
        System.out.println("Начал работать метод по измению данных пользователя");
        File fileToBeModified = new File(WorkWithFileServiceImpl.FILE_NAME);
        StringBuilder oldContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (line != null) {
                oldContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            System.out.println("Успешно получили строки из файла");
            String content = oldContent.toString();

            System.out.println("Введите строку которую хотите заменить: ");
            String oldString = console.readLine();
            System.out.println("Введите новое значение строки для замены: ");
            String newString = console.readLine();

            String newContent = content.replaceAll(oldString, newString);
            try (FileWriter writer = new FileWriter(fileToBeModified)) {
                writer.write(newContent);
                System.out.printf("Успешно заменили строку %s на строку %s\n", oldString, newString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  public void deleteUserWithReplacingToEmptyLine() {
        System.out.println("Начал работать метод по удалению пользователя");
        File fileToBeModified = new File(WorkWithFileServiceImpl.FILE_NAME);
        StringBuilder oldContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (line != null) {
                oldContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            System.out.println("Успешно получили строки из файла");
            String content = oldContent.toString();

            System.out.println("Введите фамилию пользователя, которого хотите удалить: ");
            String userInfoForDelete = console.readLine();
            String newString = "";
            String userInforForDeleteString = getUserByLastName(userInfoForDelete).toString();
            System.out.println("Получили юзера: "  + userInforForDeleteString);
            StringBuilder correctOldString = new StringBuilder();
            String symbols = "";
            for (int i = 0; i < userInforForDeleteString.length(); i++) {
                if(i == 4){
                    symbols = "\\";
                }
                correctOldString.append(symbols).append(userInforForDeleteString.charAt(i));
                symbols="";
            }
            System.out.println("Успешно заменили строку добавив символ \\" + correctOldString);
            String newContent = content.replaceAll(String.valueOf(correctOldString), newString);
            try (FileWriter writer = new FileWriter(fileToBeModified)) {
                writer.write(newContent);
                System.out.printf("Успешно удалили пользователя с фамилией %s на строку \n", userInfoForDelete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String parseUserInfoToString(User user){
        String userInfoString = "";
        userInfoString+=user.getFirstName() + user.getLastName() + user.getPatronymic() + user.getBirthdate() + user.getSex();
        return userInfoString;
    }
//    @Override
//    public List<User> updateUser(String firstParam, String secondParam) {
//        List<User> listAfterDelete = new ArrayList<>();
//        List<User> allUsers = getAllUsers();
//        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
//        System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
//        for (User user : allUsers) {
//            if (user.getLastName().equals(firstParam)) {
//                System.out.println("Нашли совпадение по: " + firstParam + "\n" + "Редактируем его");
//                user.setLastName(secondParam);
//            }
//            listAfterDelete.add(user);
//        }
//        System.out.println("Получили итоговый список: " + allUsers);
//        return listAfterDelete;
//    }



}
