package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.service.AdminService;
import com.bookstore.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/AllAuthors")
    public List<Author> getAllAuthors() {
        return userService.getAllAuthors();
    }

    @GetMapping("/AllBooks")
    public List<Book> getAllBooks() {
        return userService.getAllBooks();
    }

    @GetMapping("/BookByAuthor/{id}")
    public List<Book> getBooksByAuthor(@PathVariable("id") Long id) {
        return userService.getBooksByAuthor(id);
    }

    @GetMapping("/BookById/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return userService.getBookById(id);
    }

    @PostMapping("/addAuthor")
    public void addAuthor(@RequestBody Author author) {
        adminService.addAuthor(author);
    }

    @PostMapping("/addBookById/{id}")
    @Transactional
    public void addBookById(@PathVariable("id") Long id,
                            @RequestBody Book book) {
        adminService.addBookById(id, book);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public void DeleteAuthorById(@PathVariable("id") Long id) {
        adminService.deleteAuthor(id);
    }

    @DeleteMapping("/deleteBook/{idA}/{idB}")
    @Transactional
    public void DeleteBookById(@PathVariable("idA") Long authorId,
                               @PathVariable("idB") Long bookId) {
        adminService.deleteBook(authorId, bookId);
    }
}
