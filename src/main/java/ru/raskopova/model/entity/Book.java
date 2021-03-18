package ru.raskopova.model.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Table(name = "books")
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "book_name")
    String bookName;

}
