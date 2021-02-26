package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dtos.jsons.BookSeedDto;
import softuni.library.models.entities.Author;
import softuni.library.models.entities.Book;
import softuni.library.repositories.AuthorRepository;
import softuni.library.repositories.BookRepository;
import softuni.library.services.BookService;
import softuni.library.util.ValidatorUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.library.constants.GlobalConstants.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        return Files.readString(Path.of(BOOKS_PATH));
    }

    @Override
    public String importBooks() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        BookSeedDto[] dtos = this.gson
        .fromJson(new FileReader(BOOKS_PATH), BookSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(bookSeedDto -> {
                    if(this.validatorUtil.isValid(bookSeedDto)){

                        Book book = this.modelMapper.map(bookSeedDto, Book.class);

                        Author author = this.authorRepository
                                .getOne(bookSeedDto.getAuthor());

                        book.setAuthor(author);
                        this.bookRepository.saveAndFlush(book);
                        sb.append(String.format("Successfully imported Book: %s written in %s",
                                bookSeedDto.getName(), bookSeedDto.getWritten()));
                    }else {
                        sb.append("Invalid Book");
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }
}
