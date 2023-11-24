package library.controller;

import library.service.BookService;
import library.service.BorrowedBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
