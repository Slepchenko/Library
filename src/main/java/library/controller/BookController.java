package library.controller;

import library.model.Book;
import library.service.FileService;
import library.service.SimpleBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping ({"/", "/{id}"})
    public String getById(@PathVariable int id, Model model) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }
        model.addAttribute("book", optionalBook.get());
//        String a = fileService.getFileById(optionalBook.get().getFileId()).get().getPath();
//        System.err.println(a);
        model.addAttribute("file", fileService.getFileById(optionalBook.get().getFileId()).get().getPath());
        return "books/book";
    }

}
