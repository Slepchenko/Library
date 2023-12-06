package library.repository;

import library.model.BorrowedBook;

import java.util.Collection;
import java.util.Optional;

public interface BorrowedBookRepository {

    Optional<BorrowedBook> findById(int id);

    Collection<BorrowedBook> findAll();

    Optional<BorrowedBook> save(BorrowedBook borrowedBook);

    boolean deleteById(int id);

    boolean checkBook(BorrowedBook borrowedBook);

    Optional<BorrowedBook> findByBookId(int bookId);

}
