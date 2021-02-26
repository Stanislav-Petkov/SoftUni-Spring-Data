package com.example.advancedqueringex.services;


import com.example.advancedqueringex.entities.Book;
import com.example.advancedqueringex.entities.EditionType;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    //Problem 1 Write a program that prints the titles of all books, for which the age restriction matches the given
    // input (minor, teen or adult). Ignore casing of the input.
    List<Book> getAllBooksByAgeRestriction(String ageRestriction);

    //Problem 2
    List<Book> findAllBooksByEditionTypeAndCopies(String editionType , int copies);

    //:Problem 3
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThanPrice
            , BigDecimal higherThanPrice);

    //Problem 4
    List<Book> getAllBooksByReleaseDateNotInYear(int year);

    //Problem 5
    List<Book> getAllByReleaseDate(String date);

    //Problem 7
    List<Book> getAllBooksByTitleContainsString(String string);

    //Problem 8
    List<Book> findBooksByAuthor_LastNameStartingWith(String startString);

    //Problem 8 with JPQL
    List<Book> findBooksByAuthor_LastNameStartingWithJPQL(String startString);

    //Problem 9
    int findAllByTitleIsLongerThan(int length);

    //Problem 10 Solution 2
    int getTotalCopiesByAuthor(String name);

    //Problem 11
    String findTitleEditionTypeAgeRestrictionPriceByTitle(String title);

    //Problem 11
    String[] findTitleEditionTypeAgeRestrictionPriceByTitleJPQL(String title);

    //Problem 12 part 1/2
    int findAllByReleaseDateAfter(LocalDate localDate);

    //Problem 12 part 2/2
    void updateBookCopiesAfterADate(int value);

    //Problem 12 Solution 2
    int updateBooksCopiesAfterDate(String date, int copies);

    //Problem 14
    int findNumberOfBooksByAuthorFirstAndLastName(String firstName, String lastName);

//    List<Book> getAllBooksAfter2000();
//
//    List<Book> findAllBooksByAuthorGeorgePowellOrderedByReleaseDate();
}
