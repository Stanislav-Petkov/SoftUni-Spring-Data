package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.services.models.BookInfo;

import java.io.IOException;

public interface AuthorService {
    void seedAuthorsInDb() throws IOException;

    void printAllByFirstNameEndingWith(String ending);

    //Ex 10
    void printAllAuthorsByBookCopies();

}
