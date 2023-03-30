package org.example.workWithFileService;

import java.util.List;

public interface WorkWithFIleService {

    List<String> getListFromFile();

    void writeUserDataToFile(String userInfo);
}
