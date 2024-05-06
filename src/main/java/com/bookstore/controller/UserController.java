package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/AllAuthors")
    public List<Author> GetAllAuthors(){
        return userService.GetAllAuthors();
    }

    @GetMapping("/AllBooks")
    public List<Book> GetAllBooks(){
        return userService.GetAllBooks();
    }

    @GetMapping("/BookByAuthor/{id}")
    public List<Book> GetBooksByAuthor(@PathVariable("id") Long id){
        return userService.GetBooksByAuthor(id);
    }
}
