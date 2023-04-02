package org.example.services.workWithFileService;

import org.example.utilClasses.User;

import java.util.List;

public interface WorkWithFIleService {
    /**
     * Получаем строку - все строки прочитанные из файла
     * @return
     */
    List<String> getListFromFile();

    void writeUserDataToFile(User user);

    //public void writeUserFromConsoleToFile();
}
