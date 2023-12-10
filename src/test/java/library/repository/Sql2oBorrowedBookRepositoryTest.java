package library.repository;

import library.configuration.DatasourceConfiguration;
import library.model.Book;
import library.model.BorrowedBook;
import library.model.File;
import library.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oBorrowedBookRepositoryTest {

    private static Sql2oBorrowedBookRepository sql2oBorrowedBookRepository;

    private static Sql2oBookRepository sql2oBookRepository;

    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2oFileRepository sql2oFileRepository;

    private static Book book;

    private static User user;

    private static File file;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = Sql2oBookRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");
        DatasourceConfiguration configuration = new DatasourceConfiguration();
        DataSource datasource = configuration.connectionPool(url, username, password);
        Sql2o sql2o = configuration.databaseClient(datasource);
        sql2oBorrowedBookRepository = new Sql2oBorrowedBookRepository(sql2o);
        sql2oFileRepository = new Sql2oFileRepository(sql2o);
        sql2oBookRepository = new Sql2oBookRepository(sql2o);
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
        file = new File("fileName1", "filePath3");
        sql2oFileRepository.save(file);
        user = new User(0, "Ivan", "ivan@mail.ru", "password");
        sql2oUserRepository.save(user);
        book = new Book(0, "bookName", "authorName", 200, 100, "genreName", file.getId(), "testDesc");
        sql2oBookRepository.save(book);

    }

    @AfterAll
    public static void delete() {
        sql2oUserRepository.delete(user.getEmail(), user.getPassword());
        sql2oBookRepository.deleteById(book.getId());
        Collection<File> files = sql2oFileRepository.findAll();
        for (File f : files) {
            System.err.println("it was worked??? A, blya");
            sql2oFileRepository.deleteById(f.getId());
        }
    }

    @AfterEach
    public void deleteBorrowedBooks() {
        Collection<BorrowedBook> borrowedBooks = sql2oBorrowedBookRepository.findAll();
        for (BorrowedBook bb : borrowedBooks) {
            sql2oBorrowedBookRepository.deleteById(bb.getId());
        }
    }

    @Test
    public void whenFindByIdThenTrue() {
        BorrowedBook borrowedBook = new BorrowedBook(0, book.getId(), user.getId(), book.getDepositPrice(), book.getRentalPrice(), 1, 0);
        BorrowedBook saveBorrowedBook = sql2oBorrowedBookRepository.save(borrowedBook);
        BorrowedBook foundBorrowedBook = sql2oBorrowedBookRepository.findById(borrowedBook.getId()).get();
        assertThat(foundBorrowedBook).usingDefaultComparator().isEqualTo(saveBorrowedBook);
    }

    @Test
    public void whenFindAllThenTrue() {
        User user1 = new User(0, "Ivan", "ivan@mail.ru", "password");
        User user2 = new User(0, "Alexander", "asslepchenko@bk.ru", "12345");
        sql2oUserRepository.save(user1);
        sql2oUserRepository.save(user2);
        Collection<User> foundUsers = sql2oUserRepository.findAll();
        assertThat(foundUsers).isEqualTo(List.of(user1, user2));
    }

}