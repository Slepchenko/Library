package library.repository;

import library.model.User;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Collection;
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
                    INSERT INTO users(name, email, password)
                    VALUES (:name, :email, :password)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("name", user.getName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Exception exception) {
            exception.getMessage();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(
                    "SELECT * FROM users WHERE email = :email and password = :password")
                    .addParameter("email", email)
                    .addParameter("password", password);
            User user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public String findUserNameById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT name FROM users WHERE id = :id")
                    .addParameter("id", id);
            User user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return user.getName();
        }
    }

    public boolean delete(String email, String password) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery
                            ("DELETE FROM users WHERE email = :email and password = :password")
                    .addParameter("email", email)
                    .addParameter("password", password);
            int affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    public Collection<User> findAll() {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM users");
            return query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetch(User.class);
        }
    }

}
