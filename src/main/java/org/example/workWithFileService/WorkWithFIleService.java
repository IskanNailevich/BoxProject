package org.example.workWithFileService;

import java.util.List;

public interface WorkWithFIleService {

    List<String> getListFromFile();

    void writeToFile(String userInfo);
}
