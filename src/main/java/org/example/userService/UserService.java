package org.example.userService;

import org.example.UtilClasses.User;

import java.util.List;

public interface UserService {

    List<User> deleteUser(String string);

    List<User> getAllUsers();

    User getUser(String string);

    public void update();



}