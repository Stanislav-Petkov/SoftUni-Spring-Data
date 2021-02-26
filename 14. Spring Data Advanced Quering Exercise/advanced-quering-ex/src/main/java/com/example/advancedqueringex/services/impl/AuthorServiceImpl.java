package com.example.advancedqueringex.services.impl;


import com.example.advancedqueringex.entities.Author;
import com.example.advancedqueringex.repositories.AuthorRepository;
import com.example.advancedqueringex.services.AuthorService;
import com.example.advancedqueringex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.advancedqueringex.constants.GlobalConstants.AUTHORS_FILE_PATH;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count() != 0){
            return;
        }

        String[] fileContent = fileUtil.readFileContent(AUTHORS_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author =new Author(params[0],params[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBook();
    }

    @Override
    public List<Author> findAllAuthorsByBooksReleaseDateBefore1990() {
        return this.authorRepository.findAllAuthorsByBookWithRelease();
    }

    @Override
    public List<Author> getAuthorsByFirstNameEndsWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndsWith(endsWith);
    }

    @Override
    public int findSumOfCopiesByAuthorNames(String firstName, String lastName) {
        return this.authorRepository.findSumOfCopiesByAuthorNames(firstName,lastName);
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();

        // We should know the whole count of authors from the repository, so we make the method getAllAuthorsCount()
        // We have 30 authors , in order to avoid that the random is from 0 to 29 we add 1

        // nextInt returns from 0 inclusive, but in sql we do not have Author id 0, they start from 1
        // nextInt receives specified value (exclusive) if we set this.authorService.getAllAuthorsCount()
        // exclusively it is from 0 to 29
        int randomId = random.nextInt(this.getAllAuthorsCount() ) + 1;
        // Now the range is from 1 to 30 inclusive

        return this.findAuthorById((long) randomId);
    }
}
