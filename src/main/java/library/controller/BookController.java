package library.controller;

import library.filter.AddUserModel;
import library.model.Book;
import library.service.BookService;
import library.service.BorrowedBookService;
import library.service.FileService;
import library.service.SimpleBookService;
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
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final FileService fileService;

    private final BorrowedBookService borrowedBookService;

    public BookController(
            SimpleBookService bookService, FileService fileService, BorrowedBookService borrowedBookService) {
        this.bookService = bookService;
        this.fileService = fileService;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("borrowedBooks", borrowedBookService.findAll());
        return "books/list";
    }

    @GetMapping ({"/", "/{id}"})
    public String getById(@PathVariable int id, Model model, HttpSession session) {
        AddUserModel.checkInMenu(model, session);
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }

        if (borrowedBookService.findByBookId(id).isPresent()) {
            model.addAttribute("message", "Книга уже выдана на руки");
            return "errors/404";
        }
        model.addAttribute("book", optionalBook.get());
        model.addAttribute("deposit", "Залог " + optionalBook.get().getDepositPrice() + " рублей");
        model.addAttribute("rental", "Стоимость аренды книги " + optionalBook.get().getRentalPrice() + " рублей в месяц");
        model.addAttribute("file", fileService.getFileById(optionalBook.get().getFileId()).get().getPath());
        return "books/book";
    }

}
