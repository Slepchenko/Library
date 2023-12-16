package library.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class BorrowedBook {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "book_id", "bookId",
            "user_id", "userId",
            "total", "total",
            "term", "term",
            "borrow_date", "borrowDate",
            "refund_date", "refundDate",
            "forfeit_count", "forfeitCount",
            "institution", "institution",
            "student", "student"
    );

    private int id;

    private int bookId;

    private int userId;

    private int total;

    private int term;

    private LocalDateTime borrowDate = LocalDateTime.now();

    private LocalDateTime refundDate;

    private int forfeitCount;

    private String institution;

    private boolean student;

    public BorrowedBook() {
    }

    public BorrowedBook(int id, int bookId, int userId, int total, int term, int forfeitCount, String institution, boolean student) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.total = total;
        this.term = term;
        this.forfeitCount = forfeitCount;
        this.institution = institution;
        this.student = student;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public boolean getStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
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
