package ru.raskopova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raskopova.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
