package org.example.draft;

import org.example.services.workWithFileService.WorkWithFileServiceImpl;
import org.example.utilClasses.User;

import java.io.*;

public class draft {
    //FROM UserServiceStreamImpl

    //    @Override
//    public List<User> deleteUserWithReturningNewList(String lastName) {
//        List<User> listAfterDelete = new ArrayList<>();
//        List<User> allUsers = getAllUsers();
//        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
//        for (User user : allUsers) {
//            if (user.getLastName().equals(lastName)) {
//                System.out.println("Нашли совпадение по: " + lastName + "\n" + "Пропускаем  его  добавление");
//                continue;
//            }
//            listAfterDelete.add(user);
//        }
//        System.out.println("Получили итоговый список без запрашиваемого пользователя: " + allUsers);
//        return listAfterDelete;
//    }
//
//



    //    public void update() {
//        System.out.println("Начал работать метод по измению данных пользователя");
//        File fileToBeModified = new File(WorkWithFileServiceImpl.FILE_NAME);
//        StringBuilder oldContent = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
//             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
//            String line = reader.readLine();
//            while (line != null) {
//                oldContent.append(line).append(System.lineSeparator());
//                line = reader.readLine();
//            }
//            System.out.println("Успешно получили строки из файла");
//            String content = oldContent.toString();
//
//            System.out.println("Введите строку которую хотите заменить: ");
//            String oldString = console.readLine();
//            System.out.println("Введите новое значение строки для замены: ");
//            String newString = console.readLine();
//
//            String newContent = content.replaceAll(oldString, newString);
//            try (FileWriter writer = new FileWriter(fileToBeModified)) {
//                writer.write(newContent);
//                System.out.printf("Успешно заменили строку %s на строку %s\n", oldString, newString);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    //    @Override
//    public List<User> updateUser(String firstParam, String secondParam) {
//        List<User> listAfterDelete = new ArrayList<>();
//        List<User> allUsers = getAllUsers();
//        System.out.println("Получили лист со всеми юзерами: " + getAllUsers());
//        System.out.println("Сравниваем параметр пользователя по запрашиваемой фамилии");
//        for (User user : allUsers) {
//            if (user.getLastName().equals(firstParam)) {
//                System.out.println("Нашли совпадение по: " + firstParam + "\n" + "Редактируем его");
//                user.setLastName(secondParam);
//            }
//            listAfterDelete.add(user);
//        }
//        System.out.println("Получили итоговый список: " + allUsers);
//        return listAfterDelete;
//    }



//    public void deleteUserWithReplacingToEmptyLine() {
//        System.out.println("Начал работать метод по удалению пользователя");
//        File fileToBeModified = new File(WorkWithFileServiceImpl.FILE_NAME);
//        StringBuilder oldContent = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
//             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
//            String line = reader.readLine();
//            while (line != null) {
//                oldContent.append(line).append(System.lineSeparator());
//                line = reader.readLine();
//            }
//            System.out.println("Успешно получили строки из файла");
//            String content = oldContent.toString();
//
//            System.out.println("Введите айди пользователя, которого хотите удалить: ");
//            int userInfoForDelete = Integer.parseInt(console.readLine());
//            String newString = "";
//            String userInforForDeleteString = getById(userInfoForDelete).toString();
//            System.out.println("Получили юзера: "  + userInforForDeleteString);
//            StringBuilder correctOldString = new StringBuilder();
//            String symbols = "";
//            for (int i = 0; i < userInforForDeleteString.length(); i++) {
//                if(i == 4){
//                    symbols = "\\";
//                }
//                correctOldString.append(symbols).append(userInforForDeleteString.charAt(i));
//                symbols="";
//            }
//            System.out.println("Успешно заменили строку добавив символ \\" + correctOldString);
//            String newContent = content.replaceAll(String.valueOf(correctOldString), newString);
//            try (FileWriter writer = new FileWriter(fileToBeModified)) {
//                writer.write(newContent);
//                System.out.printf("Успешно удалили пользователя с фамилией %s на строку \n", userInfoForDelete, newString);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



//    public String parseUserInfoToString(User user){
//        String userInfoString = "";
//        userInfoString+=user.getFirstName() + user.getLastName() + user.getPatronymic() + user.getBirthdate() + user.getSex();
//        return userInfoString;
//    }

    //FROM WORKWITHFILESERVICE_______________________________________________________________________________________________

    // @Override
    //public void writeUserFromConsoleToFile() {
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
//            String createdUserFromConsole = createUserService.createUser().toString();
//            bufferedWriter.write(createdUserFromConsole);
//            bufferedWriter.close();
//        } catch (IOException e) {
//            System.out.println("Ошибка записи в файл" + e);
//            throw new RuntimeException(e);
//        }
    //}
}
