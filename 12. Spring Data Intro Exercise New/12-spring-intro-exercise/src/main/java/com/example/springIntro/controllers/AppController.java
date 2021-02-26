package com.example.springIntro.controllers;

import com.example.springIntro.entities.Author;
import com.example.springIntro.entities.Book;
import com.example.springIntro.services.AuthorService;
import com.example.springIntro.services.BookService;
import com.example.springIntro.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

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
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        //Ex.1
//        this.printNamesOfBooksAfter2000Ex1();
        //Ex.2
//        this.findAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990Ex2();
        //Ex.3
//        this.findAllAuthorsByCountOfBooksEx3();
        //Ex.4
//        this.findAllBooksByAuthorNames();

    }

    private void findAllBooksByAuthorNames() {
        this.bookService.findAllBooksByAuthorNames()
                .forEach(book -> System.out.printf("%s %s %d %n",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()));
    }

    private void findAllAuthorsWithAtLeastOneBookWithReleaseDateBefore1990Ex2() {
        List<Author> allByBooksReleaseDate = this.authorService.findAllByBooksReleaseDate();

        this.authorService.findAllByBooksReleaseDate()
                .forEach(author -> {
                    System.out.printf("%s %s %n",
                            author.getFirstName(),
                            author.getLastName());
                });
    }

    private void findAllAuthorsByCountOfBooksEx3() {
        this.authorService.findAllAuthorsByCountOfBooks()
                .forEach(author -> {
                    System.out.printf("%s %s %d %n",
                            author.getFirstName(),author.getLastName(),
                            author.getBooks().size());
                });
    }

    private void printNamesOfBooksAfter2000Ex1() {
        List<Book> books = this.bookService.getAllBooksAfter2000();
        books.forEach(book -> {
            System.out.println(book.getTitle());
        });
    }
}
