package library.repository;

import library.model.Book;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

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

    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("DELETE FROM books WHERE id = :id");
            int affectedRows = query.addParameter("id", id).executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    public Book save(Book book) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                      INSERT INTO books(name, author, deposit_price, rental_price, genre, file_id, description)
                      VALUES (:name, :author, :depositPrice, :rentalPrice, :genre, :fileId, :description)
                      """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("name", book.getName())
                    .addParameter("author", book.getAuthor())
                    .addParameter("depositPrice", book.getDepositPrice())
                    .addParameter("rentalPrice", book.getRentalPrice())
                    .addParameter("genre", book.getGenre())
                    .addParameter("fileId", book.getFileId())
                    .addParameter("description", book.getDescription());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            book.setId(generatedId);
            return book;
        }
    }

}
