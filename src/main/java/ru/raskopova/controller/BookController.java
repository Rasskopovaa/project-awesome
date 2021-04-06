package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;
import ru.raskopova.service.BookService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/getAllBook")
    public String getAllBook(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "addBook";
    }

    @GetMapping("/getBook/{id}/edit")
    public String getBook(@PathVariable(value = "id") int id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "mainBook";
    }

    @PostMapping("/getBook/{id}/edit")
    public String updateBook(@PathVariable(value = "id") int id, @RequestParam(value = "bookName") String name, Model model) {
        bookService.updateBook(name, id);
        return "addBook";
    }

    @PostMapping("/createBook")
    public String createBook(BookDTO bookDTO, Model model) {
        bookService.addBook(bookDTO);
        return "addBook";
    }

    @GetMapping("/createBook")
    public String createBookForm() {
        return "addBook";
    }

    @PostMapping("/getBook/{id}/remove")
    public String deleteBook(@PathVariable(value = "id") int id) {
        bookService.deleteBook(id);
        return "addBook";
    }
}
