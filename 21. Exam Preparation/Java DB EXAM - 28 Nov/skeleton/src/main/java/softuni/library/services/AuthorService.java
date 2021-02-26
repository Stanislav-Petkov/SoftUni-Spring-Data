package softuni.library.services;

import softuni.library.models.entities.Author;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AuthorService {
    boolean areImported();
    String readAuthorsFileContent() throws IOException;
    String importAuthors() throws FileNotFoundException;
    Author findByFirstNameAndLastName(String firstName, String lastName);
}
