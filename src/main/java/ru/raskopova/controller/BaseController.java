package ru.raskopova.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


}
