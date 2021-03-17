package ru.raskopova.service;

import ru.raskopova.model.dto.BookDTO;

import java.util.List;


public interface BookService {
    /**
     * @return
     */
    List<BookDTO> getAllBooks();
}
