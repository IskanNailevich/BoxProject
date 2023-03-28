package org.example;

import org.example.convertService.ListConverter;
import org.example.convertService.ListConverterImpl;
import org.example.userService.UserService;
import org.example.userService.UserServiceStreamImpl;
import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        UserService userService = new UserServiceStreamImpl();
        userService.createUsers(null);
        System.out.println(userService.getAllUsers());
        String rakhimov = userService.getUser("Rakhimov").getFirstName();
        System.out.println(rakhimov);
        List<User> users = userService.deleteUser("Rakhimov");
        System.out.println(users);
        List<User> users1 = userService.updateUser("Rakhimov", "Petrov");
        System.out.println(users1);
        userService.createUser();


    }
}
