package library.controller;

import library.service.SimpleBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ThreadSafe
@Controller
@RequestMapping("/books")
public class BookController {

    private final SimpleBookService bookService;

    public BookController(SimpleBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

}
