package library.controller;

import library.FinallyPrice;
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

    private BorrowedBook saveBorrowedBook;

    private int discountRental;

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
        saveBorrowedBook = borrowedBook;
        Book book = bookService.findById(borrowedBook.getBookId()).get();
        discountRental = FinallyPrice.getFinallyPrice(saveBorrowedBook.getTerm(), book.getRentalPrice());
        if (borrowedBook.getDeposit() > book.getDepositPrice()) {
            model.addAttribute("message", "Сумма превосходит трубуемую, попробуйте ещё раз");
            return "/errors/404";
        }
        if (borrowedBook.getDeposit() < book.getDepositPrice()) {
            model.addAttribute("message", "Внесенной суммы недостаточно, попробуйте ещё раз");
            return "/errors/404";
        }
        model.addAttribute("borrowedBook", borrowedBook);
        model.addAttribute("bookMessage", book.getName());
        model.addAttribute("termMessage", borrowedBook.getTerm() + " месяц(ев)");
        model.addAttribute("depositMessage", borrowedBook.getDeposit() + " рублей");
        model.addAttribute("priceMessage", discountRental + " рублей");
        model.addAttribute("discountMessage", FinallyPrice.discount(saveBorrowedBook.getTerm()));
        return "/borrowedBooks/pay";
    }

    @PostMapping("/pay")
    public String save(Model model, @ModelAttribute BorrowedBook borrowedBook, HttpSession session) {
        checkInMenu(model, session);
        saveBorrowedBook.setRental(borrowedBook.getRental());
        model.addAttribute("borrowedBook", saveBorrowedBook);
        if (saveBorrowedBook.getRental() > discountRental) {
            model.addAttribute("message", "Сумма превосходит трубуемую, попробуйте ещё раз");
            return "/errors/404";
        }
        if (saveBorrowedBook.getRental() < discountRental) {
            model.addAttribute("message", "Внесенной суммы недостаточно, попробуйте ещё раз");
            return "/errors/404";
        }
        try {
            borrowedBookService.save(saveBorrowedBook);
            return "/borrowedBooks/successfully";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        if (!borrowedBookService.deleteById(id)) {
            model.addAttribute("message", "Книга с указанным идентификатором не найдена");
        }
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
