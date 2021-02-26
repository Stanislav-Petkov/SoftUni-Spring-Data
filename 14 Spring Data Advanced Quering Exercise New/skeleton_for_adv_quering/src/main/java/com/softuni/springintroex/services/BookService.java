package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.AgeRestriction;
import com.softuni.springintroex.services.models.BookInfo;

import java.io.IOException;
import java.time.LocalDate;

public interface BookService {
    void seedBooksInDb() throws IOException;

    void findAllByAgeRestriction(String ageRestriction);

    void printAllBooksByEditionTypeAndCopies();

    void findAllByPriceLessThanAndPriceGreaterThan();

    void printAllBooksNotInYear(String year);

    void printAllBookWithReleaseDateLessThan(String localDate);

    void printAllBooksWithAuthorLastNameStartingWith(String startString);

    void printAllBookTitlesContaining(String containingString);

    void printNumberOfBooksWithTitlesLongerThan(int minLength);

    BookInfo findBookByTitle(String title);

    void printUpdatedCopiesCount(String date, int copies);

}
