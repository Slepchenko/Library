package library.controller;

import library.model.Book;
import library.model.User;
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

    private final SimpleBookService bookService;

    private final FileService fileService;

    public BookController(SimpleBookService bookService, FileService fileService) {
        this.bookService = bookService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        model.addAttribute("books", bookService.findAll());
        checkInMenu(model, session);
        return "books/list";
    }

    @GetMapping ({"/", "/{id}"})
    public String getById(@PathVariable int id, Model model, HttpSession session) {
        checkInMenu(model, session);
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }
        model.addAttribute("book", optionalBook.get());
        model.addAttribute("deposit", "Залог " + optionalBook.get().getDepositPrice() + " рублей");
        model.addAttribute("rental", "Стоимость аренды книги " + optionalBook.get().getRentalPrice() + " рублей в месяц");
        model.addAttribute("file", fileService.getFileById(optionalBook.get().getFileId()).get().getPath());
        return "books/book";
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
