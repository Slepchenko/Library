package library.repository;

import library.configuration.DatasourceConfiguration;
import library.model.Book;
import library.model.File;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oBookRepositoryTest {

    private static Sql2oBookRepository sql2oBookRepository;

    private static Sql2oFileRepository sql2oFileRepository;

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

        sql2oBookRepository = new Sql2oBookRepository(sql2o);
        sql2oFileRepository = new Sql2oFileRepository(sql2o);
        file = new File("testName", "testPath5");
        sql2oFileRepository.save(file);
    }

    @AfterAll
    public static void deleteFile() {
        sql2oFileRepository.deleteById(file.getId());
    }

    @AfterEach
    public void clearBooks() {
        Collection<Book> books = sql2oBookRepository.findAll();
        for (var book : books) {
            sql2oBookRepository.deleteById(book.getId());
        }
    }

    @Test
    public void whenFindByIdThenTrue() {
        Book book = new Book(0, "testName", "testAuthor", 200, 100, "testGenre", file.getId(), "testDesc");
        Book saveBook = sql2oBookRepository.save(book);
        Optional<Book> foundBook = sql2oBookRepository.findById(saveBook.getId());
        assertThat(foundBook.get()).usingDefaultComparator().isEqualTo(saveBook);
    }

    @Test
    public void whenFindAllThenTrue() {
        Book book1 = new Book(0, "testName1", "testAuthor1", 200, 100, "testGenre1", file.getId(), "testDesc1");
        Book book2 = new Book(0, "testName2", "testAuthor2", 250, 150, "testGenre2", file.getId(), "testDesc2");
        sql2oBookRepository.save(book1);
        sql2oBookRepository.save(book2);
        Collection<Book> foundUsers = sql2oBookRepository.findAll();
        assertThat(foundUsers).isEqualTo(List.of(book1, book2));
    }

}