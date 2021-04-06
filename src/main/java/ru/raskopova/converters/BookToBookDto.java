package ru.raskopova.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;

@Component
public class BookToBookDto implements Converter<Book, BookDTO> {
    /**
     * конвертирует Book в BookDTO
     *
     * @param book
     * @return
     */
    @Override
    public BookDTO convert(Book book) {
        return new BookDTO().setId(book.getId())
                .setBookName(book.getBookName());
    }
}

