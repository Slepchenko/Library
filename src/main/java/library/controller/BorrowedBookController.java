package library.controller;

import library.model.Book;
import library.model.BorrowedBook;
import library.model.User;
import library.service.BookService;
import library.service.BorrowedBookService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
@RequestMapping("/borrowedBooks")
public class BorrowedBookController {

    private final BookService bookService;

    private final BorrowedBookService borrowedBookService;

    private  BorrowedBook saveBook;

    public BorrowedBookController(BookService bookService, BorrowedBookService borrowedBookService) {
        this.bookService = bookService;
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping("/{id}")
    public String getCreationPage(Model model, @PathVariable int id, HttpSession session) {
        checkInMenu(model, session);
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            model.addAttribute("message", "Книга не найдена");
            return "errors/404";
        }
        model.addAttribute("deposit", "В качестве залога нужно внести " + optionalBook.get().getDepositPrice() + " рублей");
        model.addAttribute("book", optionalBook.get());
        model.addAttribute("monthNum", new int[]{1, 2, 3, 4, 5, 6});
        return "borrowedBooks/create";
    }

    @PostMapping("/execution")
    public String execution(Model model, @ModelAttribute BorrowedBook borrowedBook, HttpSession session) {
        checkInMenu(model, session);
        String reference = "/borrowedBooks/pay";
        saveBook = borrowedBook;
        Book book = bookService.findById(borrowedBook.getBookId()).get();
        model.addAttribute("borrowedBook", borrowedBook);
        model.addAttribute("bookMessage", book.getName());
        model.addAttribute("termMessage", borrowedBook.getTerm() + " месяц(ев)");
        model.addAttribute("depositMessage", borrowedBook.getDeposit() + " рублей");
        model.addAttribute("priceMessage", book.getRentalPrice() + " рублей");
        model.addAttribute("discountMessage", "0" + " рублей");
        return validAmount(borrowedBook.getDeposit(), book.getDepositPrice(), model, reference);
    }

    @PostMapping("/pay")
    public String save(Model model, @ModelAttribute BorrowedBook borrowedBook, HttpSession session) {
        checkInMenu(model, session);
        String reference = "/borrowedBooks/successfully";
        saveBook.setRental(borrowedBook.getRental());
        Book book = bookService.findById(saveBook.getBookId()).get();
        model.addAttribute("borrowedBook", saveBook);
        String result = validAmount(saveBook.getRental(), book.getRentalPrice(), model, reference);
        try {
            borrowedBookService.save(saveBook);
            return result;
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    private void checkInMenu(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }

    private String validAmount(int amount, int price, Model model, String reference) {
        String result = reference;
        if (Integer.compare(amount, price) == 1) {
            model.addAttribute("message", "Сумма превосходит трубуемую, попробуйте ещё раз");
            result = "/errors/404";
        }
        if (Integer.compare(amount, price) == -1) {
            model.addAttribute("message", "Внесенной суммы недостаточно, попробуйте ещё раз");
            result = "/errors/404";
        }
        return result;
    }

}
