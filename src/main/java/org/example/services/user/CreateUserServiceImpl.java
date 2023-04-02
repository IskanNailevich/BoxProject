package org.example.services.user;

import org.example.utilClasses.User;
import org.example.services.convert.ListConverter;
import org.example.services.convert.ListConverterImpl;
import org.example.services.workWithFileService.WorkWithFIleService;
import org.example.services.workWithFileService.WorkWithFileServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Класс по созданию юзера
 */
public class CreateUserServiceImpl implements CreateUserService {

    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;

    public CreateUserServiceImpl() {
        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
    }
 //   @Override
//    public void pushCreatedUsersToFile() {
//        List<User> myUsersList = new ArrayList<>();//todo
//        myUsersList.add(new User("Iskander",
//                "Zagidullin",
//                "Nailevich",
//                LocalDate.of(1994, 6, 1),
//                "M"));
//        myUsersList.add(new User("Ruslan",
//                "Mukametzyanov",
//                "Albertovich",
//                LocalDate.of(1991, 12, 5),
//                "M"));
//        myUsersList.add(new User("Dzhamil",
//                "Rakhimov",
//                "Ilshatovich",
//                LocalDate.of(1992, 7, 5),
//                "M"));
//        List<String> usersToString = myUsersList.stream().map(User::toString).collect(Collectors.toList());
//        System.out.println("Print list usersToString");
//        System.out.println(usersToString);
//        //workWithFIleService.writeUserDataToFile(usersToString.toString());
//
//    }
}
