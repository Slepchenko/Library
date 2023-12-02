package library.controller;

import library.model.Book;
import library.model.BorrowedBook;
import library.model.User;
import library.service.BookService;
import library.service.BorrowedBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/personalAccount")
public class PersonalAccountController {

    BookService bookService;

    BorrowedBookService borrowedBookService;

    public PersonalAccountController(BookService bookService, BorrowedBookService borrowedBookService) {
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping("/personalAccountPage")
    public String personalAccount(Model model, HttpSession session) {
        checkInMenu(model, session);
        model.addAttribute("borrowedBooks", borrowedBookService.findAll());
        model.addAttribute("books", bookService.findAll());
        return "personalAccount/personalAccount";
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
