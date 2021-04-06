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
    private Integer id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @OneToOne()
    @JoinColumn(name = "role_id")
    Role userRole;


}
