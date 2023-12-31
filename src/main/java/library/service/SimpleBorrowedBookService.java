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
        BorrowedBook borrowedBook = borrowedBookRepository.findById(id).get();
        return borrowedBookRepository.findById(id);
    }

    @Override
    public Collection<BorrowedBook> findAll() {
        return borrowedBookRepository.findAll();
    }

    @Override
    public BorrowedBook save(BorrowedBook borrowedBook) {
        return borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public boolean deleteById(int id) {
        return borrowedBookRepository.deleteById(id);
    }

    @Override
    public boolean checkBook(BorrowedBook borrowedBook) {
        return borrowedBookRepository.checkBook(borrowedBook);
    }

    @Override
    public Optional<BorrowedBook> findByBookId(int bookId) {
        return borrowedBookRepository.findByBookId(bookId);
    }

}
