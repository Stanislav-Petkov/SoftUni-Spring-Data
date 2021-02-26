package com.example.advancedqueringex.services;


import com.example.advancedqueringex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByCountOfBooks();

    List<Author> findAllAuthorsByBooksReleaseDateBefore1990();

    //Problem 6 Write a program that prints
    // the names of those authors, whose first name ends with a given string.
    List<Author> getAuthorsByFirstNameEndsWith(String endsWith);

    //Problem 10
    int findSumOfCopiesByAuthorNames(String firstName, String lastName);
}
