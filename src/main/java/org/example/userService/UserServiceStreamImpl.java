package org.example.userService;

import org.example.User;
import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.workWithFileService.WorkWithFIleService;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceStreamImpl implements UserService {
    private WorkWithFIleService workWithFIleService;
    private ListConverter listConverter;
    public UserServiceStreamImpl(){
        workWithFIleService = new WorkWithFileServiceImpl();
        listConverter = new ListConverterImpl();
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        List<String> listFromFile = workWithFIleService.getListFromFile();
        List<User> users = listConverter.stringToUsers(listFromFile);
        return users;
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
                LocalDate.of(1994,6,1),
                "M"));
        myUsersList.add(new User("Mukhametzyanov",
                "Ruslan",
                "Albertovich",
                LocalDate.of(1991,12,5),
                "M"));
        myUsersList.add(new User("Rakhimov",
                "Dzhamil",
                "Ilshatovich",
                LocalDate.of(1992,7,5),
                "M"));
//        System.out.println("check");
//        System.out.println(myUsersList);
//        System.out.println("after check");
        List<String> usersToString = myUsersList.stream().map(user -> user.toString()).collect(Collectors.toList());
        System.out.println("Print list usersToString");
        System.out.println(usersToString);

        workWithFIleService.writeToFile(usersToString.toString());

    }
}
