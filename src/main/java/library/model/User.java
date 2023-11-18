package library.model;

import java.util.Map;
import java.util.Objects;

public class User {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "surname", "surname",
            "email", "email",
            "password", "password"
    );

    private int id;

    private String surname;

    private String name;

    private String patronymic;

    private int telephoneNumber;

    private int increaseSpend;

    private String email;

    private String password;

    public User() {
    }

    public User(
            int id, String surname, String name, String patronymic,
            int telephoneNumber, String email, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIncreaseSpend() {
        return increaseSpend;
    }

    public void setIncreaseSpend(int increaseSpend) {
        this.increaseSpend = increaseSpend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
