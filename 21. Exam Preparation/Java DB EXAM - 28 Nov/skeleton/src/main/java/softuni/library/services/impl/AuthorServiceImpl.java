package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.constants.GlobalConstants;
import softuni.library.models.dtos.jsons.AuthorSeedDto;
import softuni.library.models.entities.Author;
import softuni.library.repositories.AuthorRepository;
import softuni.library.services.AuthorService;
import softuni.library.util.ValidatorUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.library.constants.GlobalConstants.AUTHORS_PATH;
import static softuni.library.constants.GlobalConstants.BOOKS_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        return Files.readString(Path.of(AUTHORS_PATH));
    }

    @Override
    public String importAuthors() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        AuthorSeedDto[] dtos = this.gson.fromJson(new FileReader(AUTHORS_PATH), AuthorSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(authorSeedDto -> {
                    if(this.validatorUtil.isValid(authorSeedDto)){
                        if(this.authorRepository.findByFirstNameAndLastName(authorSeedDto.getFirstName(),
                                authorSeedDto.getLastName()) == null) {
                            Author author = this.modelMapper.map(authorSeedDto, Author.class);

                            this.authorRepository.saveAndFlush(author);
                            sb.append(String.format("Successfully imported Author: %s %s - %s",
                                    authorSeedDto.getFirstName(), authorSeedDto.getLastName(),
                                    authorSeedDto.getBirthTown()));
                        }else {
                            sb.append("Author already in db");
                        }
                    }else {
                        sb.append("Invalid Author");
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public Author findByFirstNameAndLastName(String firstName, String lastName) {
        return this.authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
