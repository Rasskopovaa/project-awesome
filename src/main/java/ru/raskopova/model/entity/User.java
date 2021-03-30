package ru.raskopova.model.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Data
@Table(name = "users")
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @OneToOne
    Role role;


}
