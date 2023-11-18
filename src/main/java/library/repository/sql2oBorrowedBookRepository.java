package library.repository;

import library.model.BorrowedBook;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class sql2oBorrowedBookRepository implements BorrowedBookRepository {

    @Override
    public Optional<BorrowedBook> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<BorrowedBook> findAll() {
        return null;
    }

}
