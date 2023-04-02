package org.example.services.workWithFile;

import org.example.utilClasses.User;

import java.util.List;

public interface WorkWithFIle {
    /**
     * Получаем строку - все строки прочитанные из файла
     *
     * @return
     */
    List<String> getListFromFile();

    /**
     * Записывает переданного пользователя в файл.
     * isRewrite - булевая переменная. true -> добавит поверх старых данных, false -> добавит, перезаписав старые
     *
     * @param user
     * @param isRewrite
     */
    void writeUserDataToFile(User user, boolean isRewrite);
}
