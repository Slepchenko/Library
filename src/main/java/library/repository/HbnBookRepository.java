package library.repository;

import library.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnBookRepository implements BookRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Book> findById(int id) {
        return crudRepository.optional(
                "from Book f join fetch f.category where f.id = :fId",
                Book.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Book> findAll() {
        return crudRepository.query("from Book f join fetch f.category", Book.class);
    }
}
