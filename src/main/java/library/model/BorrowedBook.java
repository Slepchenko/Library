package library.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class BorrowedBook {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "book_id", "bookId",
            "user_id", "userId",
            "deposit", "deposit",
            "rental", "rental",
            "term", "term",
            "borrow_date", "borrowDate",
            "refund_date", "refundDate",
            "forfeit_count", "forfeitCount"
    );

    private int id;

    private int bookId;

    private int userId;

    private int deposit;

    private int rental;

    private int term;

    private LocalDateTime borrowDate = LocalDateTime.now();

    private LocalDateTime refundDate;

    int forfeitCount;

    public BorrowedBook() {
    }

    public BorrowedBook(int id, int bookId, int userId, int deposit, int rental, int term, int forfeitCount) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.deposit = deposit;
        this.rental = rental;
        this.term = term;
        this.forfeitCount = forfeitCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getRental() {
        return rental;
    }

    public void setRental(int rental) {
        this.rental = rental;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getForfeitCount() {
        return forfeitCount;
    }

    public void setForfeitCount(int forfeitCount) {
        this.forfeitCount = forfeitCount;
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
