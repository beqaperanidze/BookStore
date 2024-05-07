package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Long id) {
        if (authorRepository.findById(id).isEmpty())
            throw new IllegalStateException("Author not found");
        else
            return authorRepository.findById(id).get().getBooks();
    }

    public Book getBookById(Long id) {
        if (bookRepository.findById(id).isEmpty())
            throw new IllegalStateException("Book not found");
        else return bookRepository.findById(id).get();
    }
}
