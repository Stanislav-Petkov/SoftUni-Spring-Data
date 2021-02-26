package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.*;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.domain.repositories.BookRepository;
import com.softuni.springintroex.domain.repositories.CategoryRepository;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.softuni.springintroex.constants.GlobalConstants.BOOKS_FILE_PATH;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void seedBooksInDb() throws IOException {
        //1 20/01/1998 27274 15.31 2 Absalom
        String[] lines = this.fileUtil.readFileContent(BOOKS_FILE_PATH);
        Random random = new Random();
        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            long authorIndex = random.nextInt((int) this.authorRepository.count())+1;
            Author author = this.authorRepository.findById(authorIndex).get();
            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(tokens[1], formatter);
            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                titleBuilder.append(tokens[i]).append(" ");
            }

            String title = titleBuilder.toString().trim();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(getRandomCategories());

            bookRepository.saveAndFlush(book);

        }

    }

    @Override
    public void findAllByAgeRestriction(String ageRestriction) {
        AgeRestriction ageRestriction1 = AgeRestriction.valueOf(ageRestriction.toUpperCase());
        this.bookRepository.findAllByAgeRestriction(ageRestriction1)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksByEditionTypeAndCopies() {
        this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000)
        .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void findAllByPriceLessThanAndPriceGreaterThan() {
        BigDecimal minPrice = new BigDecimal("5.0");
        BigDecimal maxPrice = new BigDecimal("40.0");
        this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(minPrice,maxPrice)
                .forEach(book -> System.out.println(book.getTitle() + " - $" +
                        book.getPrice()));
    }

    @Override
    public void printAllBooksNotInYear(String year) {
        this.bookRepository.findAllByReleaseDateNotInYear(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBookWithReleaseDateLessThan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        this.bookRepository.findAllByReleaseDateIsLessThan(localDate)
                .forEach(book -> System.out.printf("%s %s %s %n",
                        book.getTitle(),book.getEditionType(),
                        book.getPrice()));
    }

    @Override
    public void printAllBooksWithAuthorLastNameStartingWith(String startString) {
        this.bookRepository.findAllByAuthorLastNameStartWith(startString)
                .forEach(book -> System.out.printf("%s (%s %s) %n",
                        book.getTitle(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()));
    }

    @Override
    public void printAllBookTitlesContaining(String containingString) {
        this.bookRepository.findAllByTitleContaining(containingString)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printNumberOfBooksWithTitlesLongerThan(int minLength) {
        int size = this.bookRepository.findAllByTitleIsGreaterThan(minLength);
        System.out.printf("There are %d books with longer title than %d symbols",size,minLength);

    }

    @Override
    public BookInfo findBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        BookInfo bookInfo = new BookInfo(book.getTitle(),
               book.getEditionType(), book.getAgeRestriction(),
                book.getPrice());
        return bookInfo;
    }

    @Override
    public void printUpdatedCopiesCount(String date, int copies) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate localDate = LocalDate.parse(date,dtf);
        int updatedRows = this.bookRepository.updateCopies(copies, localDate);
        System.out.println(updatedRows * copies);
    }


    private Set<Category> getRandomCategories(){
        Random random = new Random();
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            long categoryIndex = random.nextInt((int) this.categoryRepository.count()) + 1;
            Category category = this.categoryRepository.findById(categoryIndex).get();
            categories.add(category);
        }
        return categories;
    }
}
