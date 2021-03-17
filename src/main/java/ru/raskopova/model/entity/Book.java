package ru.raskopova.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "book_name")
    String bookName;

}
