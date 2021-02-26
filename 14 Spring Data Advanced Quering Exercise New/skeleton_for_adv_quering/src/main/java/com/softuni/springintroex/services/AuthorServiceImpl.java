package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.domain.entities.Author;
import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softuni.springintroex.constants.GlobalConstants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthorsInDb() throws IOException {
        String[] lines = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            Author author = new Author(tokens[0],tokens[1]);
            authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public void printAllByFirstNameEndingWith(String ending) {
        this.authorRepository.findAllByFirstNameEndingWith(ending)
                .forEach(author -> System.out.println(author.getFirstName() + " " +
                        author.getLastName()));
    }

    @Override
    public void printAllAuthorsByBookCopies() {
        List<Author> authors = this.authorRepository.findAll();
        Map<String, Integer> authorCopies = new HashMap<>();

        authors.forEach(author -> {
                    int copies = author
                            .getBooks()
                            .stream()
                            .mapToInt(Book::getCopies).sum();
                    authorCopies.put(author.getFirstName() + " " + author.getLastName(), copies);
                });

        authorCopies.entrySet().stream()
                .sorted((current, next) -> Integer.compare(next.getValue(), current.getValue()))
                .forEach(author -> System.out.printf("%s %d %n", author.getKey(), author.getValue()));
    }


}
