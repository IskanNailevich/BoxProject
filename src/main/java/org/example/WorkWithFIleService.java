package org.example;

import java.io.IOException;
import java.util.List;

public interface WorkWithFIleService {

    List<String> getListFromFile();

    void writeToFile(String userInfo);
}