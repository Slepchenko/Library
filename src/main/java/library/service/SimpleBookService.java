package library.service;

import library.model.Book;
import library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleBookService implements BookService {

    private final BookRepository bookRepository;

    public SimpleBookService(BookRepository sql2oBookRepository) {
        this.bookRepository = sql2oBookRepository;
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

}
