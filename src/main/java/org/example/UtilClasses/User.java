package org.example.UtilClasses;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private String firstName;
    private String lastName;
    private String patronymic;
    //DateTimeFormatter birthDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private LocalDate birthdate ;

    private String sex;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", sex='" + sex + '\'' +
                "};";
    }
}
