package library.service;

import library.model.BorrowedBook;

import java.util.Collection;
import java.util.Optional;

public interface BorrowedBookService {

    Optional<BorrowedBook> findById(int id);

    Collection<BorrowedBook> findAll();

}
