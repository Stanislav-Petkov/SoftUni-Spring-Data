package com.example.advancedqueringex.controllers;


import com.example.advancedqueringex.entities.AgeRestriction;
import com.example.advancedqueringex.entities.Book;
import com.example.advancedqueringex.entities.EditionType;
import com.example.advancedqueringex.services.AuthorService;
import com.example.advancedqueringex.services.BookService;
import com.example.advancedqueringex.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        // seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        //Problem 1 Write a program that prints the titles of all books, for which the age restriction
        //matches the given input (minor, teen or adult). Ignore casing of the input.
//        System.out.println("Enter age restriction: ");
//        this.bookService
//                .getAllBooksByAgeRestriction(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //Problem 2 Write a program that prints the titles
        // of the golden edition books, which have less than 5000 copies.
//        this.bookService
//                .findAllBooksByEditionTypeAndCopies("GOLD", 5000)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //Problem 3 Write a program that prints the titles and prices of
        // books with price lower than 5 and higher than 40.

//        this.bookService
//                .findAllByPriceLessThanOrPriceGreaterThan(new BigDecimal(5), new BigDecimal(40))
//                .forEach(b -> System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice()));

        //Problem 4 Write a program that prints the titles of all books that
        // are NOT released in a given year.
//        System.out.println("Enter a year: ");
//
//        this.bookService
//                .getAllBooksByReleaseDateNotInYear(
//                        Integer.parseInt(this.bufferedReader.readLine()))
//                .forEach(b -> {
//                    System.out.printf("%s %n", b.getTitle());
//                });

        //Problem 5 Write a program that prints the title, the edition type and the price
        // of books, which are released before a given date. The date will be in the format dd-MM-yyyy.
//        System.out.println("Enter a date dd-MM-yyyy: ");
//        this.bookService.getAllByReleaseDate(
//                this.bufferedReader.readLine())
//                .forEach(b -> System.out.printf("%s %s %.2f%n",
//                        b.getTitle(),b.getEditionType(),b.getPrice()));

        //Problem 6 Write a program that prints the names of those authors,
        // whose first name ends with a given string.
//        System.out.println("Enter ending: ");
//        this.authorService.getAuthorsByFirstNameEndsWith(
//                this.bufferedReader.readLine())
//                .forEach(a -> System.out.printf("%s %s%n",
//                        a.getFirstName(), a.getLastName()));

        //Problem 7 Write a program that prints the titles of books,
        // which contain a given string (regardless of the casing).
//        System.out.println("Enter containing str: ");
//        this.bookService.getAllBooksByTitleContainsString(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);
//        }

        //Problem 8 Write a program that prints the titles of books, which are
        // written by authors, whose last name starts with a given string.
//        System.out.println("Enter start of last name: ");
//        this.bookService
//                .findBooksByAuthor_LastNameStartingWith(this.bufferedReader.readLine())
//                .forEach(b -> System.out.printf("%s (%s %s)%n",
//                        b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));

        //Problem 8
//        System.out.println("Enter start of last name: ");
//        this.bookService
//                .findBooksByAuthor_LastNameStartingWithJPQL(this.bufferedReader.readLine())
//                .forEach(b -> System.out.printf("%s (%s %s)%n",
//                        b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));

        //Problem 9 Write a program that prints the number of books, whose title is longer than a given number.
//        System.out.println("Enter length of title: ");
//        int numberOfBooks = this.bookService
//                .findAllByTitleIsLongerThan(Integer.parseInt(this.bufferedReader.readLine()));
//        System.out.println(numberOfBooks);

        //Problem 10 Write a program that prints the total number of book copies by author.
        // Order the results descending by total book copies.
//        System.out.println("Enter author names like: ");
//        System.out.println("Randy click Enter");
//        System.out.println("Graham click Enter:");
//        String firstName = this.bufferedReader.readLine();
//        String lastName = this.bufferedReader.readLine();
//        int sumOfCopiesByAuthorNames = this.authorService
//                .findSumOfCopiesByAuthorNames(firstName, lastName);
//        System.out.println(firstName + " " + lastName + " - " + sumOfCopiesByAuthorNames);

        //Problem 10 Solution 2
//        System.out.println("Enter author name: ");
//        System.out.println(this.bookService.getTotalCopiesByAuthor(this.bufferedReader
//        .readLine()));
        //Problem 11 Write a program that prints information (title, edition type,
        // age restriction and price) for a book by given title. When retrieving
        // the book information select only those fields
        // and do NOT include any other information in the returned result.
//        System.out.println("Enter Book title: ");
//        String fieldsContent = this.bookService
//                .findTitleEditionTypeAgeRestrictionPriceByTitle(this.bufferedReader.readLine());
//        String[] fields = fieldsContent.split(",");
//        System.out.print(fields[0] + " ");
//        EditionType[] values = EditionType.values();
//        for (EditionType value : values) {
//            if(value.ordinal() == Integer.parseInt(fields[1])){
//                System.out.print(value + " ");
//            }
//        }
//        AgeRestriction[] ages = AgeRestriction.values();
//        for (AgeRestriction value : ages) {
//            if(value.ordinal() == Integer.parseInt(fields[2])){
//                System.out.print(value + " ");
//            }
//        }
//        System.out.print(fields[3]);

        //Problem 11 with jpql
//        System.out.println("Enter Book title: ");
//        String[] fieldsContent = this.bookService
//                .findTitleEditionTypeAgeRestrictionPriceByTitleJPQL(this.bufferedReader.readLine());
//        for (String field : fieldsContent) {
//            System.out.print(field + " ");
//        }

        //Problem 12

//        System.out.println("Enter a date in format dd MMM yyyy:");
//        String date = this.bufferedReader.readLine();
//        System.out.println("Enter value: ");
//        int value = Integer.parseInt(this.bufferedReader.readLine());
//        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
//        int books = this.bookService.findAllByReleaseDateAfter(localDate);
//        this.bookService.updateBookCopiesAfterADate(value);
//        System.out.println(books * value);

        //Problem 12 Solution 2
//        System.out.println("Enter date and copies:");
//        String date = this.bufferedReader.readLine();
//        int copies = Integer.parseInt(this.bufferedReader.readLine());
//
//        int totalCopies = this.bookService.updateBooksCopiesAfterDate(date,copies) * copies;
//
//        System.out.println(totalCopies);

        //Problem 14 Using Workbench (or other similar tool) create a stored procedure, which
        // receives an author’s first and last name and returns the total amount of books the
        // author has written. Then write a program that receives an author’s name and prints
        // the total number of
        // books the author has written by using the stored procedure you’ve just created.
//        System.out.println("Enter first name and last name:");
//        String firstName = this.bufferedReader.readLine();
//        String lastName = this.bufferedReader.readLine();
//        int countOfBooks = this.bookService.findNumberOfBooksByAuthorFirstAndLastName(firstName, lastName);
//        System.out.println(firstName + " " + lastName + " has written "+ countOfBooks + " books");
    }
}

/*
        //Ex 1.	Get all books after the year 2000.
//        List<Book> books = this.bookService.getAllBooksAfter2000();
//        books.forEach(b -> {
//            System.out.printf("%s%n",
//                    b.getTitle());
//        });

        // Ex.2.Get all authors with at least one book with release date before 1990.
        // Print their first name and last name.

//        this.authorService.findAllAuthorsByBooksReleaseDateBefore1990()
//                .forEach(a -> {
//                    System.out.printf("%d %s %s %d%n",
//                           a.getId(), a.getFirstName(), a.getLastName(), a.getBooks().size());
//                });
        //Ex 3.	Get all authors, ordered by the number of their books (descending).
        // Print their first name, last name and book count.

        this.authorService.findAllAuthorsByCountOfBooks()
                .forEach(a -> {
                    System.out.printf("%s %s %d%n",
                            a.getFirstName(),a.getLastName(),a.getBooks().size());
                });

        //4.	Get all books from author George Powell, ordered by their release date (descending),
        // then by book title (ascending). Print the book's title, release date and copies.

//        this.bookService.findAllBooksByAuthorGeorgePowellOrderedByReleaseDate()
//                .forEach(b -> {
//                    System.out.printf("%s %s %s%n",
//                            b.getTitle(), b.getReleaseDate(), b.getCopies());
//                });

        /*
        SELECT *
        FROM authors AS a
        INNER JOIN books b on a.id = b.author_id
        WHERE a.first_name = 'George' && a.last_name = 'Powell'
        ORDER BY b.release_date DESC, b.title ASC ;
         */

