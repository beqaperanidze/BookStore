package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AdminService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void addAuthor(Author author) {
        if (authorRepository.findById(author.getId()).isPresent()) {
            throw new IllegalArgumentException("Author already exists");
        } else authorRepository.save(author);
    }

    @Transactional
    public void addBookById(Long authorId, Book book) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("Author not found");
        } else {
            Author author = authorOptional.get();
            if (book.getId() == null) {
                bookRepository.save(book);
            }
            List<Book> books = author.getBooks();
            books.add(book);
            author.setBooks(books);
            authorRepository.save(author);
        }
    }

    public void deleteAuthor(Long id) {
        if (authorRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Author not found");
        } else authorRepository.delete(authorRepository.findById(id).get());
    }

    @Transactional
    public void deleteBook(Long authorId, Long bookId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("Author not found");
        } else {
            Author author = authorOptional.get();
            List<Book> books = author.getBooks();

            Optional<Book> bookOptional = books.stream()
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst();

            if (bookOptional.isEmpty()) {
                throw new IllegalArgumentException("Book not found for the author");
            } else {
                Book bookToRemove = bookOptional.get();
                books.remove(bookToRemove);
                author.setBooks(books);
                authorRepository.save(author);
                bookRepository.deleteById(bookId);
            }
        }
    }

}
