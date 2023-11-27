package library.service;

import library.model.Book;
import library.model.BorrowedBook;
import library.repository.BookRepository;
import library.repository.BorrowedBookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleBookService implements BookService {

    private final BookRepository bookRepository;

    private final BorrowedBookRepository borrowedBookRepository;

    public SimpleBookService(BookRepository sql2oBookRepository, BorrowedBookRepository borrowedBookRepository) {
        this.bookRepository = sql2oBookRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    public Collection<BorrowedBook> findAllBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }

}
