package library.controller;

import library.logic.AddUserModel;
import library.service.BookService;
import library.service.BorrowedBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
        AddUserModel.checkInMenu(model, session);
        model.addAttribute("borrowedBooks", borrowedBookService.findAll());
        model.addAttribute("books", bookService.findAll());
        return "personalAccount/personalAccount";
    }

}
