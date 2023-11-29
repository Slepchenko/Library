package library.repository;

import library.model.Book;
import library.model.BorrowedBook;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oBorrowedBookRepository implements BorrowedBookRepository {

    private final Sql2o sql2o;

    public Sql2oBorrowedBookRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<BorrowedBook> findById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM borrowed_books WHERE id = :id");
            query = query.addParameter("id", id);
            BorrowedBook borrowedBook = query.setColumnMappings(BorrowedBook.COLUMN_MAPPING).executeAndFetchFirst(BorrowedBook.class);
            return Optional.ofNullable(borrowedBook);
        }
    }

    @Override
    public Collection<BorrowedBook> findAll() {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM borrowed_books");
            return query.setColumnMappings(BorrowedBook.COLUMN_MAPPING).executeAndFetch(BorrowedBook.class);
        }
    }

    @Override
    public Optional<BorrowedBook> save(BorrowedBook borrowedBook) {
        try (var connection = sql2o.open()) {
            String sql = """
                      INSERT INTO borrowed_books(book_id, user_id, deposit, rental, term, borrow_date, refund_date)
                      VALUES (:bookId, :userId, :deposit, :rental, :term, :borrowDate, :refundDate)
                      """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("bookId", borrowedBook.getBookId())
                    .addParameter("userId", borrowedBook.getUserId())
                    .addParameter("deposit", borrowedBook.getDeposit())
                    .addParameter("rental", borrowedBook.getRental())
                    .addParameter("term", borrowedBook.getTerm())
                    .addParameter("borrowDate", borrowedBook.getBorrowDate())
                    .addParameter("refundDate", borrowedBook.getBorrowDate().plusMonths(borrowedBook.getTerm()));
            int generatedId = query.executeUpdate().getKey(Integer.class);
            borrowedBook.setId(generatedId);
            borrowedBook.setRefundDate(borrowedBook.getBorrowDate().plusMonths(borrowedBook.getTerm()));
            return Optional.of(borrowedBook);
        }
    }

}
