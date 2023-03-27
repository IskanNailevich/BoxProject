package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateUser {

    public CreateUser() throws IOException {

    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String name = reader.readLine();

    String lastName = reader.readLine();

    String patronymic  = reader.readLine();

    String phoneNumber = reader.readLine();

    String sex = reader.readLine();

    String birthDate = reader.readLine();

    User user = new User(name, lastName, patronymic,phoneNumber, sex, birthDate);

}
