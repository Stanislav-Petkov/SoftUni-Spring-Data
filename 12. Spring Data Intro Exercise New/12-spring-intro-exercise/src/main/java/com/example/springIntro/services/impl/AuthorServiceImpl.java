package com.example.springIntro.services.impl;

import com.example.springIntro.constants.GlobalConstants;
import com.example.springIntro.entities.Author;
import com.example.springIntro.repositories.AuthorRepository;
import com.example.springIntro.services.AuthorService;
import com.example.springIntro.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.springIntro.constants.GlobalConstants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count() != 0){
            return;
        }
        String[] authors = this.fileUtil
                .readFileContent(AUTHORS_FILE_PATH);
        Arrays.stream(authors)
                .forEach(a -> {
                    String[] params = a.split("\\s+");
                    Author author = new Author(params[0],params[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository
                .getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBooks();
    }

    @Override
    public List<Author> findAllByBooksReleaseDate() {
        return this.authorRepository.findAllByBooksReleaseDate();
    }
}
