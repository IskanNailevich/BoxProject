package org.example.services.convert;

import org.example.utilClasses.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ListConverterImpl implements ListConverter {
    User testUser = new User();

    private static final String delimiter = "-----------------------------------------------------------------------------";

    @Override
    public List<String> usersToString(List<User> users) {
        List<String> usersToString = users.stream()
                .map(User::toString)
                .collect(Collectors.toList());
        return usersToString;
    }

    @Override
    public List<User> stringToUsers(List<String> list) {

     //   System.out.println("Старт работы конвертера со стринга на юзер лист");
        List<String> usersParams = getUsersParams(list);

        List<User> usersResult =  splitParams(usersParams);

        //System.out.println("Конвертер успешно завершил работу");
        return usersResult;
    }

    private List<User> splitParams(List<String> list) {
        List<User> usersResult = new ArrayList<>();
        String[] stringsArr = new String[6];
        int[] intsArr = new int[3];

     //   System.out.println("Сплитим параметры по запятой");
        for (String user : list) {
            String[] splitParam = user.split(",");

//            System.out.println("Отображение каждого пользователя после слпита: ");
//            for (String s : splitParam) {
//                System.out.println("Элемент слпита : " + s);
//            }

    //        System.out.println(delimiter);
            substringParamBySplitDelimiter(splitParam, stringsArr, intsArr);

            usersResult.add(new User(Integer.parseInt(stringsArr[0]),stringsArr[1],
                    stringsArr[2],
                    stringsArr[3],
                    LocalDate.of(intsArr[0], intsArr[1], intsArr[2]),
                    stringsArr[5]));
        }
        return usersResult;
    }

    private void substringParamBySplitDelimiter(String[] splitParam, String[] stringsArr, int[] intsArr) {
    //    System.out.println("Выделяем подстроку из элемента сплита по ' ");
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
              //  System.out.println("Подстрока: " + resultParam);
            }
    }

    private List<String> getUsersParams(List<String>list) {
        Stream<String> user = list.stream()
                .filter(x -> x.contains("User"));
         //       .peek(x -> System.out.println("Пользователь = " + x));
     //   System.out.println("Получили отфильтрованный список стринг 1 этап: ");
        Stream<String> stringStream = user.map(x -> {
            int open = x.indexOf("{") + 1;
            int close = x.indexOf("}");
            return x.substring(open, close);
        });
    //    System.out.println(delimiter);
     //   System.out.println("Оставляем пользователя только с его параметрами");
        return stringStream
             //   .peek(System.out::println)
                .collect(Collectors.toList());
    }
}