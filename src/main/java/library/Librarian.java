package library;

import library.model.Book;
import library.model.BorrowedBook;

import java.time.format.DateTimeFormatter;

public class Librarian {

    public String forfeitMessage(int forfeitCount) {
        return switch (forfeitCount) {
            case 0 -> "Книга в идеальном состоянии, всё хорошо, можете сдавать!";
            case 25 -> "Книга имеет незначительные повреждения, за которые нужно доплатить. С вас:";
            default -> "Книга имеет серьезные повреждения которые Вам обойдутся в:";
        };
    }

    public String receipt(BorrowedBook borrowedBook, Book book, String userName) {
        StringBuilder message = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        message.append("Квитанция для получения книги на имя: ")
                .append(userName)
                .append(System.lineSeparator())
                .append("Название книги: ").append(book.getName()).append(System.lineSeparator())
                .append("Сроком до: ").append(borrowedBook.getRefundDate().format(formatter)).append(System.lineSeparator())
                .append("Внесенный залог: ").append(borrowedBook.getDeposit()).append(" рублей").append(System.lineSeparator())
                .append("Внесенная сумма аренды книги: ").append(borrowedBook.getRental()).append(" рублей").append(System.lineSeparator())
                .append(System.lineSeparator()).append(System.lineSeparator())
                .append("Библиотекарь Слепченко Александр Сергеевич ").append(borrowedBook.getBorrowDate().format(formatter));
        return message.toString();
    }

}