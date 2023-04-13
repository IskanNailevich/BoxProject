package org.example.services.convert;

import org.example.utilClasses.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListConverterImpl implements ListConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListConverterImpl.class);

    @Override
    public List<User> stringToUsers(List<String> list) {
        LOGGER.info("Начал работу метод преобразования строки пользователей в отдельный объект!");
        List<String> usersParams = getUsersParams(list);
        LOGGER.info("Метод по преобразованию в объект закончил работу!");
        return splitParams(usersParams);
    }

    /**
     * У полученного листа разделяем пользователей по " , ".
     *
     * @param list Лист со строками которых надо разделить по " , ".
     * @return Лист с пользователями после разделения по " , ".
     */
    private List<User> splitParams(List<String> list) {
        LOGGER.info("Начал работу метод по разделению пользователей по запятой!");
        List<User> usersResult = new ArrayList<>();
        String[] stringsArr = new String[6];
        int[] intsArr = new int[3];
        for (String user : list) {
            String[] splitParam = user.split(",");
            substringParamBySplitDelimiter(splitParam, stringsArr, intsArr);
            usersResult.add(new User(Integer.parseInt(stringsArr[0]), stringsArr[1],
                    stringsArr[2],
                    stringsArr[3],
                    LocalDate.of(intsArr[0], intsArr[1], intsArr[2]),
                    stringsArr[5]));
        }
        LOGGER.info("Метод по разделению закончил работу!");
        return usersResult;
    }

    /**
     * Из листа полученных пользователей.
     * Фильруем чтобы было слово User.
     * Обрезаем по фигурным скобкам -> {}
     * Возвращаем параметры в виде List<String>.
     *
     * @param list Лист с пользователями.
     * @return Лист пользователями после разделения.
     */
    private List<String> getUsersParams(List<String> list) {
        LOGGER.info("Начал работу етод по филитрации пользователей по слову User начал работу!");
        Stream<String> user = list.stream()
                .filter(x -> x.contains("User"));
        Stream<String> stringStream = user.map(x -> {
            int open = x.indexOf("{") + 1;
            int close = x.indexOf("}");
            return x.substring(open, close);
        });
        LOGGER.info("Метод по фильтрации закончил работу!");
        return stringStream
                .collect(Collectors.toList());
    }

    /**
     * Достаем значения из переданного массива параметров и добавляем их в строчный и численный массив.
     *
     * @param splitParam Массив с значениями после удаления символа " ' ".
     * @param stringsArr Массив с значениями после удаления символа " = ".
     * @param intsArr    Массив с значениями даты.
     */
    private void substringParamBySplitDelimiter(String[] splitParam, String[] stringsArr, int[] intsArr) {
        LOGGER.info("Начал работу метод па работе со значениями из массивов начал работу!");
        for (int i = 0; i < splitParam.length; i++) {
            String resultParam;
            if (splitParam[i].contains("'")) {
                resultParam = splitParam[i].substring(splitParam[i].indexOf("'") + 1, splitParam[i].lastIndexOf("'"));
                stringsArr[i] = resultParam;
            } else {
                resultParam = splitParam[i].substring(splitParam[i].indexOf("=") + 1);
                String[] dateSplit = resultParam.split("-");
                for (int j = 0; j < dateSplit.length; j++) {
                    intsArr[j] = Integer.parseInt(dateSplit[j]);
                }
            }
        }
        LOGGER.info("Метод по работе с массивами закончил работу!");
    }
}