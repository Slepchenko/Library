package library.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class BorrowedBook {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "book", "book",
            "user", "user",
            "borrow_date", "borrowDate",
            "refund_date", "refundDate",
            "user_id", "userId"
            );

    private int id;

    private Book book;

    private User user;

    private LocalDateTime borrowDate;

    private LocalDateTime refundDate;

    private int userId;

    public BorrowedBook() {
    }

    public BorrowedBook(int id, Book book, User user, LocalDateTime borrowDate, LocalDateTime refundDate, int userId) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.refundDate = refundDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDateTime refundDate) {
        this.refundDate = refundDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BorrowedBook that = (BorrowedBook) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
