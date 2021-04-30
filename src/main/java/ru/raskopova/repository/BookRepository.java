package ru.raskopova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.raskopova.model.entity.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
