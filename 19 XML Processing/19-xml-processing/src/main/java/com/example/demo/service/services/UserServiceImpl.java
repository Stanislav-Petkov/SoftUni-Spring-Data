package com.example.demo.service.services;

import com.example.demo.data.entities.Phone;
import com.example.demo.data.entities.User;
import com.example.demo.data.repositories.PhoneRepository;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.service.dtos.PhoneDto;
import com.example.demo.service.dtos.UserDto;
import com.example.demo.service.dtos.UserRootDto;
import com.example.demo.service.dtos.UserSeedDto;
import com.example.demo.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private final PhoneRepository phoneRepository;
    private final XMLParser xmlParser;
    private final String USERS_FILE_PATH = "src/main/resources/xmls/users.xml";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper
            , PhoneRepository phoneRepository, XMLParser xmlParser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.phoneRepository = phoneRepository;
        this.xmlParser = xmlParser;
    }

    // Save UserSeedDto to the database
    @Override
    public void save(UserSeedDto userSeedDto) {
        this.userRepository.save(this.modelMapper.map(userSeedDto, User.class));
    }

//    @Override
//    public void seedUsersUserRootDtoOld() throws JAXBException, FileNotFoundException {
//        UserRootDto dtos = this.xmlParser
//                .importFromXml(UserRootDto.class,USERS_FILE_PATH);
//        for (UserDto user : dtos.getUsers()) {
//            this.userRepository.saveAndFlush(this.modelMapper
//            .map(user,User.class));
//        }
//    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {
        UserRootDto dtos = this.xmlParser
                .importFromXml(UserRootDto.class, USERS_FILE_PATH);

        for (UserDto userDto : dtos.getUsers()) {
            User user = this.modelMapper.map(userDto,User.class);
            Set<Phone> phones = new LinkedHashSet<>();
//            this.userRepository.save(user);
            // get every phoneDto for every userDto
            for (PhoneDto phoneDto : userDto.getPhoneRootDto().getPhoneDtoList()) {
                //map phoneDto to phone and save it in the database
                Phone phone = this.modelMapper.map(phoneDto, Phone.class);
                phone.setUser(user);
                phones.add(this.phoneRepository.save(phone));
                System.out.println();
            }
            user.setPhones(phones);
            this.userRepository.save(user);
            System.out.println();
        }

    }

    // Get UserDto from the database
    @Override
    public List<UserDto> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u,UserDto.class))
                .collect(Collectors.toList());
    }

    // Find a single User
    @Override
    public UserDto findById(long id) {
        User user = this.userRepository.findById(id).orElse(null);
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        UserDto user2 = this.modelMapper.map(this.userRepository.findById(id).orElse(null),UserDto.class);
        return userDto;
    }

    @Override
    public void exportUsers() throws JAXBException {
        List<UserDto> users = this.findAll();
        UserRootDto userRootDto = new UserRootDto();
        userRootDto.setUsers(users);
        this.xmlParser.exportToXML(userRootDto, USERS_FILE_PATH);
    }
}
