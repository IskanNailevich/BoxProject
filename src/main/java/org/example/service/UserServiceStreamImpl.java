package org.example.service;

import org.example.User;
import org.example.WorkWithFIleService;
import org.example.WorkWithFileServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceStreamImpl implements UserService {
    private WorkWithFIleService workWithFIleService;
    public UserServiceStreamImpl(){
        workWithFIleService = new WorkWithFileServiceImpl();
    }

    private WorkWithFIleService workWithFIle = new WorkWithFileServiceImpl();

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void createUsers(List<User> users)  {
        List<User> myUsersList = new ArrayList<>();
        myUsersList.add(new User("Iskander",
                "Zagidullin",
                "Nailevich",
                LocalDate.of(1994,06,01),
                "M"));
        myUsersList.add(new User("Mukhametzyanov",
                "Ruslan",
                "Albertovich",
                LocalDate.of(1991,12,05),
                "M"));
        myUsersList.add(new User("Rakhimov",
                "Dzhamil",
                "Ilshatovich",
                LocalDate.of(1992,07,05),
                "M"));
        List<String> usersToString = myUsersList.stream().map(user -> user.toString()).collect(Collectors.toList());

        workWithFIleService.writeToFile(usersToString.toString());

    }
}
