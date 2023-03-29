package org.example.userService;

import org.example.UtilClasses.User;

import java.util.List;

public interface UserService {

    List<User> deleteUserWithReturningNewList(String string);

    List<User> getAllUsers();

    User getUserByLastName(String string);

    public void update();
    public String parseUserInfoToString(User user);
    public void deleteUserWithReplacingToEmptyLine();



}