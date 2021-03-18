package ru.raskopova.service;

import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;

import java.util.List;


public interface BookService {
    /**
     * возвращает список книг
     *
     * @return
     */
    List<BookDTO> getAllBooks();

    /**
     * добавляет новую книгу
     *
     * @param bookDTO
     * @return
     */
    Book addBook(BookDTO bookDTO);
}
