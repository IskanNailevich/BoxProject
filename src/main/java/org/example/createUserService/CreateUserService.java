package org.example.createUserService;

import org.example.UtilClasses.User;

import java.util.List;

public interface CreateUserService {

    User createUser();

    List<User> createAllUsers();
}
