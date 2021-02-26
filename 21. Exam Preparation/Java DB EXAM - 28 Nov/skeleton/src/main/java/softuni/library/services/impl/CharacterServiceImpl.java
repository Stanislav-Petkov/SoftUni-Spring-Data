package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.library.models.dtos.xmls.CharacterSeedRootDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Character;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.CharacterService;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.library.constants.GlobalConstants.CHARACTERS_PATH;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final BookRepository bookRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, ModelMapper modelMapper,
                                ValidatorUtil validatorUtil, XmlParser xmlParser, BookRepository bookRepository) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean areImported() {
        return this.characterRepository.count() > 0;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        return Files.readString(Path.of(CHARACTERS_PATH));
    }

    @Override
    public String importCharacters() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        CharacterSeedRootDto characterSeedRootDto = this.xmlParser
                .parseXml(CharacterSeedRootDto.class, CHARACTERS_PATH);

        characterSeedRootDto.getCharacters().stream()
                .forEach(characterSeedDto -> {


                    Character character = this.modelMapper.map(characterSeedDto, Character.class);
                    Optional<Book> book = this.bookRepository.findById(characterSeedDto.getBook().getId());
                    if (this.validatorUtil.isValid(characterSeedDto) && book.isPresent()) {
                        character.setBook(book.get());

                        this.characterRepository.saveAndFlush(character);
                        // print full name and age
                        sb.append(String.format("Successfully imported Character %s %s %s - age: %d",
                                characterSeedDto.getFirstName(), characterSeedDto.getMiddleName(),
                                characterSeedDto.getLastName(), characterSeedDto.getAge()));
                    } else {
                        sb.append("Invalid Character");
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        return this.characterRepository.findCharacterByAge()
                .stream()
                .map(character -> {
                    return String.format("\nCharacter name %s %s %s, age %d, in book %s",
                            character.getFirstName(),character.getMiddleName(),
                            character.getLastName(),character.getAge(),character.getBook().getName());
                }).collect(Collectors.joining(""));
    }
}
