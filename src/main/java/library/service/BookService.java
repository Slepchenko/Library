package library.service;

import library.model.Book;
import library.model.BorrowedBook;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(int id);

    Collection<Book> findAll();

    Collection<BorrowedBook> findAllBorrowedBooks();

}
