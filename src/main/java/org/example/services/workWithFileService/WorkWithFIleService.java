package org.example.services.workWithFileService;

import org.example.utilClasses.User;

import java.util.List;

public interface WorkWithFIleService {

    List<String> getListFromFile();

    void writeUserDataToFile(User user);

    public void writeUserFromConsoleToFile();
}
