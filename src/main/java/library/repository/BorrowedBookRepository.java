package library.repository;

import library.model.Book;
import library.model.BorrowedBook;

import java.util.Collection;
import java.util.Optional;

public interface BorrowedBookRepository {

    Optional<BorrowedBook> findById(int id);

    Collection<BorrowedBook> findAll();

}
