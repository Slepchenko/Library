package library.model;

import java.util.Map;
import java.util.Objects;

public class Book {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "author", "author",
            "deposit_price", "depositPrice",
            "rental_price", "rentalPrice",
            "genre", "genre",
            "file_id", "fileId",
            "description", "description"
    );

    private int id;

    private String name;

    private String author;

    private int depositPrice;

    private int rentalPrice;

    private String genre;

    private int fileId;

    private String description;

    public Book() {
    }

    public Book(int id, String name, String author, int depositPrice, int rentalPrice, String genre, int fileId,
                String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.depositPrice = depositPrice;
        this.rentalPrice = rentalPrice;
        this.genre = genre;
        this.fileId = fileId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(int depositPrice) {
        this.depositPrice = depositPrice;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
