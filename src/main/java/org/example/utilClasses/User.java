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
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthdate;
    private String sex;
    public int getIdWithIncrement(){
        int resultId = Integer.parseInt("" + this.id);
        setId(resultId++);
        return  resultId;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + id +'\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", sex='" + sex + '\'' +
                "};";
    }
}
