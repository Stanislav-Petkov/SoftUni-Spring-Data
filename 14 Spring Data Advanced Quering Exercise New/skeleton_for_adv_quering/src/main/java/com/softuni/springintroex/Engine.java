package com.softuni.springintroex;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Engine implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    @Autowired
    public Engine(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

        //Ex1
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        this.bookService.findAllByAgeRestriction(reader.readLine());

        //Ex2
//        this.bookService.printAllBooksByEditionTypeAndCopies();
        //ex 3
//        this.bookService.findAllByPriceLessThanAndPriceGreaterThan();
        //Ex 4
//        this.bookService.printAllBooksNotInYear(reader.readLine());
        //Ex 5
//        this.bookService.printAllBookWithReleaseDateLessThan(reader.readLine());
        //Ex 6
//        this.authorService.printAllByFirstNameEndingWith(reader.readLine());
        //Ex 7
//        this.bookService.printAllBookTitlesContaining(reader.readLine());
        //Ex 8
//        this.bookService.printAllBooksWithAuthorLastNameStartingWith(reader
//                .readLine());
        //Ex 9
//        this.bookService.printNumberOfBooksWithTitlesLongerThan(
//                Integer.parseInt(reader.readLine()));
        //Ex 10
//        this.authorService.printAllAuthorsByBookCopies();
        //Ex 11
//        BookInfo bookByTitle = this.bookService.findBookByTitle(reader.readLine());
//        System.out.println(bookByTitle.getTitle() + " " + bookByTitle.getEditionType() + " " +
//                bookByTitle.getAgeRestriction() + " " + bookByTitle.getPrice());
        //Ex 12
//        this.bookService.printUpdatedCopiesCount(reader.readLine(),Integer.parseInt(reader.readLine()));
    }

    private void seedData() throws IOException {
        categoryService.seedCategoriesInDb();
        authorService.seedAuthorsInDb();
        bookService.seedBooksInDb();
    }
}
