package org.example.convertService;

import org.example.UtilClasses.User;

import java.util.List;

public interface ListConverter {
    public List<String> usersToString(List<User> users);

    public List<User> stringToUsers(List<String> list);
}
