package library.repository;

import library.model.User;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {

    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
            String sql = """
                    INSERT INTO users(name, discount_points, email, password)
                    VALUES (:name, :discountPoints, :email, :password)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("name", user.getName())
                    .addParameter("discountPoints", user.getDiscountPoints())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            Optional<User> optionalUser = Optional.of(user);
            return optionalUser;
        } catch (Exception exception) {
            exception.getMessage();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM users WHERE email = :email and password = :password")
                    .addParameter("email", email)
                    .addParameter("password", password);
            User user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    public String findUserNameById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT name FROM users WHERE id = :id")
                    .addParameter("id", id);
            User user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return user.getName();
        }
    }

}
