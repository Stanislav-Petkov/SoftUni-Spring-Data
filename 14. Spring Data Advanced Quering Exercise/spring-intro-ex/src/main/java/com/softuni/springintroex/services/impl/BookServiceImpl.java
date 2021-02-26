package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.BOOKS_FILE_PATH;

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

                    Author author = this.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

                    // The date comes in format d/M/yyyy then releaseDate gets it in yyyy-MM-dd
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], formatter);

                    int copies = Integer.parseInt(params[2]);

                    BigDecimal price = new BigDecimal(params[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];

                    String title = this.getTitle(params);

                    Set<Category> categories = this.getRandomCategories();

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
    public List<Book> getAllBooksAfter2000() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/2000",formatter);

        return this.bookRepository
                .findAllByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Book> findAllBooksByAuthorGeorgePowellOrderedByReleaseDate() {
        return this.bookRepository.findBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(
                "George","Powell");
    }

    private Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();
        // There are 3 categories , we get a random number from 1 to 3
        Random random = new Random();
        int bound = random.nextInt(3) + 1;
        //random.nextInt(3) returns from 0 to 2
        //random.nextInt(3) + 1 returns from 1 to 3

        // From 1 to 3 times will add a random category fro 1 to 8, we have 8 categories
        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sb.append(params[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        // We should know the whole count of authors from the repository, so we make the method getAllAuthorsCount()
        // We have 30 authors , in order to avoid that the random is from 0 to 29 we add 1

        // nextInt returns from 0 inclusive, but in sql we do not have Author id 0, they start from 1
        // nextInt receives specified value (exclusive) if we set this.authorService.getAllAuthorsCount()
        // exclusively it is from 0 to 29
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount() ) + 1;
        // Now the range is from 1 to 30 inclusive

        return this.authorService.findAuthorById((long) randomId);
    }
}

















