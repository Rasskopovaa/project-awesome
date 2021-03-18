package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.service.BookService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BaseController {
    private final BookService bookService;

    @GetMapping("/")
    public String getAllBook(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "index";
    }

    @PostMapping("/create")
    public String createBook(@RequestParam(value = "bookName", required = true) BookDTO bookDTO, Model model) {
        bookService.addBook(bookDTO);
        return getAllBook(model);
    }

    @GetMapping("/create")
    public String createBookForm() {
        return "addBook";
    }

}
