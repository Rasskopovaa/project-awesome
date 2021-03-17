package ru.raskopova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;
import ru.raskopova.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookServiceImpl::convert)
                .collect(Collectors.toList());
    }

    public static BookDTO convert(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setBookName(book.getBookName());
    }
}

