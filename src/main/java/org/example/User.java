package org.example;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private String firstName;
    private String lastName;
    private String patronymic;
    //DateTimeFormatter birthDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private LocalDate birthdate ;

    private String sex;


}
