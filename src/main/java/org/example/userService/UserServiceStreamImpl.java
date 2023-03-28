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

    @Override
    public User createUser() {
        User createdUser = null;
        String firstname;
        String lastname;
        String patronymic;
        int year = 1900;
        int month = 1;
        int day = 1;
        LocalDate birthday = LocalDate.of(year, month, day);
        String sex = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите имя пользователя: ");
            firstname = scanner.nextLine();
            System.out.println("Введите фамилию пользователя: ");
            lastname = scanner.nextLine();
            System.out.println("Введите отчество пользователя: ");
            patronymic = scanner.nextLine();
            System.out.println("Введите год рождения пользователя(в числовом формате): ");
            while (scanner.hasNext()) {
                year = scanner.nextInt();
                if (year < 1900 || year > 2023) {
                    System.out.println("Введите реальный год рождения (от 1900 до 2023)");
                }
                break;
            }
            System.out.println("Введите месяц рождения (в числовом формате) : ");
            while (scanner.hasNext()) {
                month = scanner.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Введите реальный месяц (от 1 до 12)");
                }
                break;
            }
            System.out.println("Введите день рождения:");
            while (scanner.hasNext()) {
                day = scanner.nextInt();
                if (day < 1 || day > 31) {
                    System.out.println("Введите реальный месяц (от 1 до 31)");
                }
                break;
            }
            System.out.println("Введите пол пользователя (в формате: М, Ж)");
            while (scanner.hasNext()) {
                sex = scanner.nextLine();
                if (!sex.equals("M") && !sex.equals("Ж")){
                    System.out.println("Введите верное значение пола: М или Ж");
                }
                break;
            }
            createdUser = new User(firstname, lastname, patronymic, birthday, sex);
            System.out.println(createdUser);
        } catch (Exception e) {
            System.out.println("Ошибка при считывании с консоли при создании пользователя");
        }
        return createdUser;
    }

    @Override
    public void createUsers(List<User> users) {
        List<User> myUsersList = new ArrayList<>();
        myUsersList.add(new User("Iskander",
                "Zagidullin",
                "Nailevich",
                LocalDate.of(1994, 6, 1),
                "M"));
        myUsersList.add(new User("Ruslan",
                "Mukametzyanov",
                "Albertovich",
                LocalDate.of(1991, 12, 5),
                "M"));
        myUsersList.add(new User("Dzhamil",
                "Rakhimov",
                "Ilshatovich",
                LocalDate.of(1992, 7, 5),
                "M"));
        List<String> usersToString = myUsersList.stream().map(User::toString).collect(Collectors.toList());
        System.out.println("Print list usersToString");
        System.out.println(usersToString);
        workWithFIleService.writeUserDataToFile(usersToString.toString());
    }
}
