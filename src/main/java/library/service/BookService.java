package library.service;

import library.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Optional<Book> findByName(String name);

    Optional<Book> findById(int id);

    Collection<Book> findAll();

}
