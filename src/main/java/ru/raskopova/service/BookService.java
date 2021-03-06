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
     * возвращает книгу
     *
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * добавляет новую книгу
     *
     * @param bookDTO
     * @return
     */
    Book addBook(BookDTO bookDTO);

    /**
     * обновляет книгу
     *
     * @param name
     * @param id
     * @return
     */
    Book updateBook(String name, Integer id);

    /**
     * удаляет книгу
     *
     * @param id
     */
    void deleteBook(Integer id);
}
