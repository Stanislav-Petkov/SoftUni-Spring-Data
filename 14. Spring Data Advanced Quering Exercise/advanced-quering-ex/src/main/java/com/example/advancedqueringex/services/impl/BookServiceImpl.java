package com.example.advancedqueringex.services.impl;


import com.example.advancedqueringex.entities.*;
import com.example.advancedqueringex.repositories.BookRepository;
import com.example.advancedqueringex.services.AuthorService;
import com.example.advancedqueringex.services.BookService;
import com.example.advancedqueringex.services.CategoryService;
import com.example.advancedqueringex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.advancedqueringex.constants.GlobalConstants.BOOKS_FILE_PATH;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if(this.bookRepository.count() != 0){
            return;
        }

        // read content from the file;
        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");

                    Author author = this.authorService.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

                    // The date comes in format d/M/yyyy then releaseDate gets it in yyyy-MM-dd
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], formatter);

                    int copies = Integer.parseInt(params[2]);

                    BigDecimal price = new BigDecimal(params[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

                    String title = this.getTitle(params);

                    Set<Category> categories = this.categoryService.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);

                    this.bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public List<Book> getAllBooksByAgeRestriction(String ageRestriction) {
        return this.bookRepository
                .findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> findAllBooksByEditionTypeAndCopies(String editionType, int copies) {
        return this.bookRepository.
                findAllByEditionTypeAndCopiesLessThan(
                        EditionType.valueOf(editionType.toUpperCase()), copies);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThanPrice
            , BigDecimal higherThanPrice) {
        return this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lowerThanPrice, higherThanPrice);
    }

    @Override
    public List<Book> getAllBooksByReleaseDateNotInYear(int year) {
        LocalDate before = LocalDate.of(year,1,1);
        LocalDate after = LocalDate.of(year,12,31);

        return this.bookRepository
                .findAllByReleaseDateBeforeOrReleaseDateAfter(before,after);
    }

    @Override
    public List<Book> getAllByReleaseDate(String date) {
        LocalDate releaseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return this.bookRepository.findAllByReleaseDateBefore(releaseDate);
    }

    //Problem 7
    @Override
    public List<Book> getAllBooksByTitleContainsString(String word) {
        return this.bookRepository.findAllByTitleContains(word);
    }

    //Problem 8
    @Override
    public List<Book> findBooksByAuthor_LastNameStartingWith(String startString) {
        return this.bookRepository.findBooksByAuthor_LastNameStartingWith(startString);
    }

    @Override
    public List<Book> findBooksByAuthor_LastNameStartingWithJPQL(String startString) {
        return this.bookRepository.findBooksByAuthor_LastNameStartingWithJPQL(startString);
    }

    //Problem 9
    @Override
    public int findAllByTitleIsLongerThan(int length) {
        return this.bookRepository.findAllByTitleIsLongerThan(length);
    }

    @Override
    public int getTotalCopiesByAuthor(String fullName) {
        return this.bookRepository
                .findAllCopiesByAuthor(fullName);
    }

    @Override
    public String findTitleEditionTypeAgeRestrictionPriceByTitle(String title) {
        return this.bookRepository.findTitleEditionTypeAgeRestrictionPriceByTitle(title);
    }

    //Problem 11
    @Override
    public String[] findTitleEditionTypeAgeRestrictionPriceByTitleJPQL(String title) {
        return this.bookRepository.findTitleEditionTypeAgeRestrictionPriceByTitleJPQL(title)
                .split(",");
    }

    //Problem 12 part 1
    @Override
    public int findAllByReleaseDateAfter(LocalDate localDate) {
        return this.bookRepository.findAllByReleaseDateAfter(localDate);
    }

    @Override
    public void updateBookCopiesAfterADate(int value) {
        this.bookRepository.updateBookCopiesAfterADate(value);
    }

    //Problem 12 Solution 2
    @Override
    public int updateBooksCopiesAfterDate(String date, int copies) {
        LocalDate localDate = LocalDate
                .parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return this.bookRepository
                .updateAllBooksAfterGivenDate(localDate, copies);
    }

    @Override
    public int findNumberOfBooksByAuthorFirstAndLastName(String firstName, String lastName) {
        return this.bookRepository.findNumberOfBooksByAuthorFirstAndLastName(firstName, lastName);
    }

    //Poblem 11


    //    @Override
//    public List<Book> getAllBooksAfter2000() {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//        LocalDate releaseDate = LocalDate.parse("31/12/2000",formatter);
//
//        return this.bookRepository
//                .findAllByReleaseDateAfter(releaseDate);
//    }
//
//    @Override
//    public List<Book> findAllBooksByAuthorGeorgePowellOrderedByReleaseDate() {
//        return this.bookRepository.findBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(
//                "George","Powell");
//    }


    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sb.append(params[i]).append(" ");
        }
        return sb.toString().trim();
    }


}

















