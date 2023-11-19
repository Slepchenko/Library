package library.service;

import library.model.BorrowedBook;
import library.repository.BorrowedBookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleBorrowedBookService implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;

    public SimpleBorrowedBookService(BorrowedBookRepository sql2oBorrowedBookRepository) {
        this.borrowedBookRepository = sql2oBorrowedBookRepository;
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
