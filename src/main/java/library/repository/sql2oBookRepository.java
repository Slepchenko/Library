package library.repository;

import library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class sql2oBookRepository implements BookRepository {

    @Override
    public Optional<Book> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Book> findAll() {
        return null;
    }

}
