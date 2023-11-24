package library.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import library.model.Book;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oBookRepository implements BookRepository {

    private final Sql2o sql2o;

    public Sql2oBookRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Book> findById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM books WHERE id = :id");
            query = query.addParameter("id", id);
            Book book = query.setColumnMappings(Book.COLUMN_MAPPING).executeAndFetchFirst(Book.class);
            return Optional.ofNullable(book);
        }
    }

    @Override
    public Collection<Book> findAll() {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM books");
            return query.setColumnMappings(Book.COLUMN_MAPPING).executeAndFetch(Book.class);
        }
    }

}
