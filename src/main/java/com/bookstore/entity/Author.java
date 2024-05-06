package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String password;
    @OneToMany
    private List<Book> books;

    public Author(List<Book> books, String password, String surname, String name) {
        this.books = books;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }
}
