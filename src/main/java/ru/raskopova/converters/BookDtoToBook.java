package ru.raskopova.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.raskopova.model.dto.BookDTO;
import ru.raskopova.model.entity.Book;

@Component
public class BookDtoToBook implements Converter<BookDTO, Book> {

    /**
     * конвертирует bookDTO в Book
     *
     * @param bookDTO
     * @return
     */
    @Override
    public Book convert(BookDTO bookDTO) {
        return new Book()
                .setBookName(bookDTO.getBookName());
    }
}
