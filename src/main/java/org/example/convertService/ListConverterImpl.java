package org.example.convertService;

import org.example.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListConverterImpl implements ListConverter {
    @Override
    public List<String> usersToString(List<User> users) {
        List<String> usersToString = users.stream().map(User::toString).collect(Collectors.toList());
        return usersToString;
    }

    @Override
    public List<User> stringToUsers(List<String> list) {
        List<User> usersResult = new ArrayList<>();
        String[] stringsArr = new String[5];
        int[] intsArr = new int[3];
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Старт работы конвертера со стринга на юзер лист");
        List<String> usersString = list.stream().filter(x -> x.contains("User")).collect(Collectors.toList());
        System.out.println("Получили отфильтрованный список стринг 1 этап: ");
        for (String s : usersString) {
            System.out.println("Пользователь = " + s);
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Оставляем пользователя только с его параметрами");
        List<String> params = usersString.stream().map(x -> {
            int open = x.indexOf("{") + 1;
            int close = x.indexOf("}");
            return x.substring(open, close);
        }).peek(System.out::println).collect(Collectors.toList());
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Сплитим параметры по запятой");
        for (String param : params) {
            String[] splitParam = param.split(",");
            System.out.println("Отображение каждого пользователя после слпита: ");
            for (String s : splitParam) {
                System.out.println("Элемент слпита : " + s);
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Выделяем подстроку из элемента сплита по '");
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
                System.out.println("Подстрока: " + resultParam);
            }
            usersResult.add(new User(stringsArr[0], stringsArr[1], stringsArr[2], LocalDate.of(intsArr[0], intsArr[1], intsArr[2]), stringsArr[4]));
        }
        System.out.println("Конвертер успешно завершил работу");
        return usersResult;
    }
}