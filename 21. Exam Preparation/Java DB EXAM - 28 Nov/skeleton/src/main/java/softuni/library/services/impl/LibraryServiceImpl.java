package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dtos.xmls.LibraryRootSeedDto;
import softuni.library.models.dtos.xmls.LibrarySeedDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Library;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.LibraryService;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static softuni.library.constants.GlobalConstants.LIBRARIES_PATH;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, ModelMapper modelMapper,
                              ValidatorUtil validatorUtil, XmlParser xmlParser, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.bookRepository = bookRepository;
    }


    @Override
    public boolean areImported() {
        return this.libraryRepository.count() > 0;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
        return Files.readString(Path.of(LIBRARIES_PATH));
    }

    @Override
    public String importLibraries() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        LibraryRootSeedDto dtos = this.xmlParser
                .parseXml(LibraryRootSeedDto.class, LIBRARIES_PATH);

        for (LibrarySeedDto librarySeedDto : dtos.getLibraries()) {
            Library library = this.modelMapper.map(librarySeedDto, Library.class);
            Optional<Book> book = this.bookRepository.findById(librarySeedDto.getBook().getId());
            Library library1 = this.libraryRepository.findByName(librarySeedDto.getName());

            if (this.validatorUtil.isValid(librarySeedDto) && book.isPresent()
                    && library1 == null) {

                if (library.getBooks() == null) {
                    library.setBooks(new HashSet<>());
                }
                Set<Book> books = library.getBooks();
                if (!books.contains(book.get())) {
                    books.add(book.get());
                    this.libraryRepository.saveAndFlush(library);
                    sb.append(String.format("Successfully added Library: %s - %s",
                            librarySeedDto.getName(), librarySeedDto.getLocation()));
                }
            } else {
                sb.append("Invalid Library");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
