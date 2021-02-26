package com.example.springIntro.services;

import com.example.springIntro.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    List<Book> getAllBooksAfter2000();
    //Ex 4
    List<Book> findAllBooksByAuthorNames();
}
