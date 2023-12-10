package library.repository;

import library.configuration.DatasourceConfiguration;
import library.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = Sql2oUserRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");

        DatasourceConfiguration configuration = new DatasourceConfiguration();
        DataSource datasource = configuration.connectionPool(url, username, password);
        Sql2o sql2o = configuration.databaseClient(datasource);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void deleteUsers() {
        Collection<User> users = sql2oUserRepository.findAll();
        for (User u : users) {
            sql2oUserRepository.delete(u.getEmail(), u.getPassword());
        }
    }

    @Test
    public void whenFindByIdThenTrue() {
        User user = new User(0, "Ivan", "ivan@mail.ru", "password");
        Optional<User> saveUser = sql2oUserRepository.save(user);
        String foundUserName = sql2oUserRepository.findUserNameById(saveUser.get().getId());
        assertThat(foundUserName).usingDefaultComparator().isEqualTo(saveUser.get().getName());
    }

    @Test
    public void whenFindAllThenTrue() {
        User user1 = new User(0, "Ivan", "ivan@mail.ru", "password");
        User user2 = new User(0, "Alexander", "asslepchenko@bk.ru", "12345");
        sql2oUserRepository.save(user1);
        sql2oUserRepository.save(user2);
        Collection<User> foundUsers = sql2oUserRepository.findAll();
        assertThat(foundUsers).isEqualTo(List.of(user1, user2));
    }

    @Test
    public void whenFindByEmailAndPasswordThenTrue() {
        String email = "ivan@mail.ru";
        String password = "password";
        User user = new User(0, "Ivan", email, password);
        Optional<User> saveUser = sql2oUserRepository.save(user);
        Optional<User> foundUser = sql2oUserRepository.findByEmailAndPassword(email, password);
        assertThat(foundUser).isEqualTo(saveUser);
    }

    @Test
    public void whenFindByEmailAndPasswordThenFalse() {
        String email = "ivan@mail.ru";
        String password = "password";
        User user = new User(0, "Ivan", email, password);
        sql2oUserRepository.save(user);
        Optional<User> foundUser = sql2oUserRepository.findByEmailAndPassword("petrov@mail.ru", "12345");
        assertThat(foundUser).isEmpty();
    }

    @Test
    public void whenSaveThenGetSame() {
        User user = new User(0, "Ivan", "ivan@mail.ru", "password");
        Optional<User> saveUser = sql2oUserRepository.save(user);
        Optional<User> findUser = sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        assertThat(findUser).usingRecursiveComparison().isEqualTo(saveUser);
    }

    @Test
    public void whenUserWithSuchMailExistsThenGetFalse() {
        sql2oUserRepository.save(new User(0, "Ivan", "ivan@mail.ru", "password"));
        User user = new User(0, "Vanya", "ivan@mail.ru", "123");
        Optional<User> saveUser2 = sql2oUserRepository.save(user);
        assertThat(saveUser2).isEqualTo(empty());
    }

}