package ru.raskopova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;
import ru.raskopova.repository.BookRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final ConversionService conversionService;
    private final BookRepository bookRepository;

    public BookServiceImpl(@Qualifier("mvcConversionService") ConversionService conversionService, @Autowired BookRepository bookRepository) {
        this.conversionService = conversionService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> conversionService.convert(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.get();
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        return bookRepository.save(Objects.requireNonNull(conversionService.convert(bookDTO, Book.class)));
    }

    @Override
    public Book updateBook(String name, Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBookName(name);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookRepository.delete(book);
    }

}

