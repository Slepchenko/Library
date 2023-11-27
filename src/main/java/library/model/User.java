package library.model;

import java.util.Map;
import java.util.Objects;

public class User {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "discount_points", "discountPoints",
            "email", "email",
            "password", "password"
    );

    private int id;

    private String name;

    private int discountPoints;

    private String email;

    private String password;

    public User() {
    }

    public User(int id, String name, int discountPoints, String email, String password) {
        this.id = id;
        this.name = name;
        this.discountPoints = discountPoints;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountPoints() {
        return discountPoints;
    }

    public void setDiscountPoints(int discountPoints) {
        this.discountPoints = discountPoints;
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
