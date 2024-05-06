package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public UserService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> GetAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Book> GetAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> GetBooksByAuthor(Long id) {
        if (authorRepository.findById(id).isEmpty())
            throw new IllegalStateException("Author not found");
        else
            return authorRepository.findById(id).get().getBooks();
    }
}
