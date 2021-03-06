package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;
import ru.raskopova.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/admin/books")
    public String getAllBook(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "addBook";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "successReg";
    }

    @GetMapping("/admin/books/{id}/edit")
    public String getBook(@PathVariable(value = "id") int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "mainBook";
    }

    @PostMapping("/admin/books/{id}/edit")
    public String updateBook(@PathVariable(value = "id") int id,
                             @RequestParam(value = "bookName") String name, Model model) {
        bookService.updateBook(name, id);
        return getAllBook(model);
    }

    @PostMapping("/admin/books/addBook")
    public String createBook(BookDTO bookDTO, Model model) {
        bookService.addBook(bookDTO);
        return getAllBook(model);
    }

    @GetMapping("/admin/books/addBook")
    public String createBookForm(Model model) {
        return "addBook";
    }

    @PostMapping("/admin/books/{id}/edit/remove")
    public String deleteBook(@PathVariable(value = "id") int id, Model model) {
        bookService.deleteBook(id);
        return getAllBook(model);
    }
}
