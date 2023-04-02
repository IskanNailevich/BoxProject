package org.example.services.user;

import org.example.utilClasses.User;
import org.example.services.convert.ListConverter;
import org.example.services.convert.ListConverterImpl;
import org.example.services.workWithFileService.WorkWithFIleService;
import org.example.services.workWithFileService.WorkWithFileServiceImpl;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceStreamImpl implements UserService {
    private InitParams initParams;
    private ValidateParams validateParams;
    private User user;


    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;


    public UserServiceStreamImpl() {

        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
        initParams = new InitParamsImpl();
        validateParams = new ValidateParamsImpl();
        user = new User();
    }

    @Override
    public void create() {
        String[] stringsUserInfo = initParams.fullNameParams();
        int[] birthdayUserInfo = initParams.birthdayParams();
        String sexUserInfo = initParams.sexParams();

        while (!validateParams.validateFullNameParams(stringsUserInfo)) {
            System.out.println("Ошибка в ФИО параметрах при создании пользователя");
            stringsUserInfo = initParams.fullNameParams();
        }

        while (!validateParams.validateBirthdayParams(birthdayUserInfo)) {
            System.out.println("Ошибка в дате рождения при создании пользователя");
            birthdayUserInfo = initParams.birthdayParams();
        }

        while (!validateParams.validateSexParams(sexUserInfo)) {
            System.out.println("Ошибка в параметре пола при создании пользователя");
            sexUserInfo = initParams.sexParams();
        }

        System.out.println("Все параметры успешно прошли проверку, создаем пользователя");
        User ussser = new User(user.getId(), stringsUserInfo[0],
                stringsUserInfo[1],
                stringsUserInfo[2],
                LocalDate.of(birthdayUserInfo[0], birthdayUserInfo[1], birthdayUserInfo[2]),
                sexUserInfo);
        workWithFIleService.writeUserDataToFile(ussser);
        user.setId(user.getId()+1);
        System.out.println(ussser);
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
    public User get(int id) {
        User user = null;
        List<User> allUsers = getAllUsers();
        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
        System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
        for (User elementOfUsers : allUsers) {
            System.out.println("Проходимся циклом по списку, элемент из списка = " + elementOfUsers);
            System.out.println(elementOfUsers.getLastName());
            if (elementOfUsers.getId() == id) {
                user = elementOfUsers;
                System.out.println("Нашли  пользователя по айди: " + user);
            }
        }
        System.out.println(user);
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

            System.out.println("Введите айди пользователя, которого хотите удалить: ");
            int userInfoForDelete = Integer.parseInt(console.readLine());
            String newString = "";
            String userInforForDeleteString = get(userInfoForDelete).toString();
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
                System.out.printf("Успешно удалили пользователя с фамилией %s на строку \n", userInfoForDelete, newString);
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
