package library.repository;

import library.logic.Librarian;
import library.model.BorrowedBook;
import library.service.BookService;
import library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnBorrowedBookRepository implements BorrowedBookRepository {

    private final CrudRepository crudRepository;

    private final BookService bookService;

    private final UserService userService;

    @Override
    public Optional<BorrowedBook> findById(int id) {
        return crudRepository.optional(
                "from borrowed_books where id = :fId",
                BorrowedBook.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<BorrowedBook> findAll() {
        return crudRepository.query("from borrowed_books", BorrowedBook.class);
    }

    @Override
    public BorrowedBook save(BorrowedBook borrowedBook) {
        crudRepository.run(session -> session.persist(borrowedBook));
        return borrowedBook;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.query(
                "delete from borrowed_books where id = :fId",
                Map.of("fId", id)
        );
    }


    @Override
    public Optional<BorrowedBook> findByBookId(int bookId) {
        return crudRepository.optional(
                "from borrowed_books f join fetch f.priority where f.id = :fId",
                BorrowedBook.class,
                Map.of("fId", bookId)
        );
    }
}
