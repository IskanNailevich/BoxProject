package org.example.userService;

import org.example.User;

import java.util.List;

public interface UserService {

    void deleteUser(User user);

    List<User> getAllUsers();

    User getUser();

    void updateUser(User user);

    void createUser(User user);
    void createUsers (List<User> users);
}