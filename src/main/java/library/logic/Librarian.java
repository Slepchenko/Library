package library.logic;

import library.model.Book;
import library.model.BorrowedBook;

import java.time.format.DateTimeFormatter;

public class Librarian {

    private static final int NO_PRICE = 0;

    private static final int PRICE_MIN = 25;

    private static final int FIRST_LINE = 1;


    public static String forfeitMessage(int forfeitCount) {
        return switch (forfeitCount) {
            case NO_PRICE -> "Книга в идеальном состоянии, всё хорошо, можете сдавать!";
            case PRICE_MIN -> "Книга имеет незначительные повреждения, за которые нужно доплатить. С вас: ";
            default -> "Книга имеет серьезные повреждения которые Вам обойдутся в: ";
        };
    }

    public static String receipt(BorrowedBook borrowedBook, Book book, String userName) {
        StringBuilder message = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        message.append("Квитанция для получения книги на имя: ")
                .append(userName)
                .append(System.lineSeparator())
                .append("Название книги: ")
                .append(book.getName())
                .append(System.lineSeparator())
                .append("Сроком до: ")
                .append(borrowedBook.getRefundDate().format(formatter))
                .append(System.lineSeparator())
                .append("Внесенный залог: ")
                .append(book.getDepositPrice())
                .append(" рублей")
                .append(System.lineSeparator())
                .append("Внесенная сумма аренды книги с учётом скидок: ")
                .append(borrowedBook.getTotal() - book.getDepositPrice()).append(" рублей")
                .append(System.lineSeparator())
                .append("Общая сумма: ")
                .append(borrowedBook.getTotal()).append(" рублей")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Библиотекарь Слепченко Александр Сергеевич ")
                .append(borrowedBook.getBorrowDate().format(formatter));
        if (!borrowedBook.getStudent()) {
            return message.toString();
        }
        return addInstitution(message.toString(), borrowedBook.getInstitution());
    }

    private static String addInstitution(String message, String institution) {
        StringBuilder str = new StringBuilder();
        String[] array = message.split(System.lineSeparator());
        for (int i = 0; i < array.length; i++) {
            if (i == FIRST_LINE) {
                str.append("Студент университета: ").append(institution);
                str.append(System.lineSeparator());
            }
            str.append(array[i]);
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

}
