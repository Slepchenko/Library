package library.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "borrowed_books")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private int bookId;

    private int userId;

    private int term;

    private LocalDateTime borrowDate = LocalDateTime.now();

    private LocalDateTime refundDate;

    private int forfeitCount;

    private String institution;

    private boolean student;

}
