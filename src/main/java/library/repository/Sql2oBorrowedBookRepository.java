package library.repository;

import library.model.BorrowedBook;
import org.springframework.stereotype.Repository;
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
        return Optional.empty();
    }

    @Override
    public Collection<BorrowedBook> findAll() {
        return null;
    }

}
