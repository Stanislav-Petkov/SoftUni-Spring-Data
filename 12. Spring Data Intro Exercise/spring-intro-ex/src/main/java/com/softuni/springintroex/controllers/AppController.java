package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

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
    }

}

