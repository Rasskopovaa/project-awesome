package ru.bass2000.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bass2000.service.BookService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BaseController {

    private final BookService bookService;

    @GetMapping("/")
    public String welcome(Model model) {

        model.addAttribute("bookName", bookService.getBookName());
        return "index";
    }
}
