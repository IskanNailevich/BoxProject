package org.example;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class User {
    public String firstName;

    public String secondName;

    public String patronymic;

    public String phoneNumber;

    public String sex;

    DateTimeFormatter birthDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public String birthdate = birthDateFormat.format(LocalDateTime.now());

    public User(String firstName, String secondName, String patronymic, String phoneNumber, String sex, String birthdate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(patronymic, user.patronymic) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(sex, user.sex) && Objects.equals(birthDateFormat, user.birthDateFormat) && Objects.equals(birthdate, user.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, patronymic, phoneNumber, sex, birthDateFormat, birthdate);
    }
}
