package com.example.springIntro.services;

import com.example.springIntro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);
    List<Author> findAllAuthorsByCountOfBooks();
    List<Author> findAllByBooksReleaseDate();
}
