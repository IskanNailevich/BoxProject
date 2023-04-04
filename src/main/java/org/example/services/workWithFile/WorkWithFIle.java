package org.example.services.workWithFile;

import org.example.utilClasses.User;

import java.util.List;

public interface WorkWithFIle {
    /**
     * Получаем строку - все строки прочитанные из файла.
     *
     * @return возвращаем лист пользователей разделенных символом ";"
     */
    List<String> getListFromFile();

    /**
     * Записывает переданного пользователя в файл.
     *
     * @param user      Пользователь  которого надо записать в файл
     * @param isRewrite isRewrite - булевая переменная. true -> добавит поверх старых данных, false -> добавит, перезаписав старые.
     */
    void writeUserDataToFile(User user, boolean isRewrite);
}
