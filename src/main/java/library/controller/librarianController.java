package library.controller;

import library.Librarian;
import library.model.Book;
import library.model.BorrowedBook;
import library.model.User;
import library.service.BookService;
import library.service.BorrowedBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/librarian")
public class librarianController {

    private BorrowedBookService borrowedBookService;

    private BookService bookService;

    public librarianController(BorrowedBookService borrowedBookService, BookService bookService) {
        this.borrowedBookService = borrowedBookService;
        this.bookService = bookService;
    }

    @GetMapping({"/", "/{id}"})
    public String getLibrarianPage(Model model, @PathVariable int id, HttpSession session) {
        checkInMenu(model, session);
        Optional<BorrowedBook> optionalBorrowedBook = borrowedBookService.findById(id);
        if (optionalBorrowedBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }
        borrowedBookService.checkBook(optionalBorrowedBook.get());
        Optional<Book> optionalBook = bookService.findById(optionalBorrowedBook.get().getBookId());
        if (optionalBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }
        model.addAttribute("borrowedBook", optionalBorrowedBook.get());
        model.addAttribute("bookName", optionalBook.get().getName());
        Librarian librarian = new Librarian();
        int forfeitCount = optionalBorrowedBook.get().getForfeitCount();

        if (forfeitCount == 0) {
            model.addAttribute("forfeitMessage", librarian.forfeitMessage(forfeitCount));
            return "librarian/librarian";
        }
        model.addAttribute("forfeitMessage", librarian.forfeitMessage(forfeitCount));
        model.addAttribute("forfeit", forfeitCount + " рублей");
        return "librarian/librarian";
    }

    private void checkInMenu(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }

}
