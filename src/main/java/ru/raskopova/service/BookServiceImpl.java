package ru.raskopova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;
import ru.raskopova.repository.BookRepository;

import java.util.List;
import java.util.Objects;
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
    public Book addBook(BookDTO bookDTO) {
        return bookRepository.save(Objects.requireNonNull(conversionService.convert(bookDTO, Book.class)));
    }

}

