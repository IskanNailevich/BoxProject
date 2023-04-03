package org.example.utilClasses;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private int id = 0;
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthdate;
    private String sex;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id +'\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", sex='" + sex + '\'' +
                "};";
    }
}
