package library.repository;

import library.model.User;
import library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        crudRepository.run(session -> session.merge(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> create(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return crudRepository.optional("from User where email = :fEmail and password = :fPassword", User.class,
                Map.of("fEmail", "fPassword", email, password));
    }

    @Override
    public String findUserNameById(int id) {
        return crudRepository
                .optional("from User where id = :fId", User.class, Map.of("fId", id))
                .get()
                .getName();
    }
}
