package org.example;

import org.example.service.UserService;
import org.example.service.UserServiceStreamImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        UserService userService = new UserServiceStreamImpl();
        userService.createUsers(null);
    }
}
