package org.example.StartApp;

import org.example.UpdateLineFromFile;
import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.createUserService.CreateUserService;
import org.example.createUserService.CreateUserServiceImpl;
import org.example.userService.UserService;
import org.example.userService.UserServiceStreamImpl;
import org.example.workWithFileService.WorkWithFIleService;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceStreamImpl();
        CreateUserServiceImpl createUserService = new CreateUserServiceImpl();
        createUserService.pushCreatedUsersToFile();
        UpdateLineFromFile updateLineFromFile = new UpdateLineFromFile();
        userService.update();

//        WorkWithFileServiceImpl workWithFIleService = new WorkWithFileServiceImpl();
//        workWithFIleService.start();
//        System.out.println(createUserService.createUser());
//        createUserService.interUserStringsParams();
        System.out.println(userService.getAllUsers());
//        String rakhimov = userService.getUser("Rakhimov").getFirstName();
//        System.out.println(rakhimov);
//        List<User> users = userService.deleteUser("Rakhimov");
//        System.out.println(users);
//        List<User> users1 = userService.updateUser("Rakhimov", "Petrov");
//        System.out.println(users1);
//        userService.createUser();
    }
}
