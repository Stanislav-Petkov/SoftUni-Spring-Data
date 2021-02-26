package com.example.demo;

import com.example.demo.service.dtos.UserDto;
import com.example.demo.service.dtos.UserRootDto;
import com.example.demo.service.dtos.UserSeedDto;
import com.example.demo.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class CmdRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public CmdRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        UserSeedDto userSeedDto1 = new UserSeedDto("Konstantin","Mitev","Sofia");
//        UserSeedDto userSeedDto2 = new UserSeedDto("Gosho","Petkov","Varna");
//        UserSeedDto userSeedDto3 = new UserSeedDto("Nikolay","Koev","Trun");
//
//        this.userService.save(userSeedDto1);
//        this.userService.save(userSeedDto2);
//        this.userService.save(userSeedDto3);
        //Export single UserRootDto with list of UserDto
//        List<UserDto>  users = this.userService.findAll();
//        UserRootDto user = new UserRootDto();
//        user.setUsers(users);

        //export single userDto
//        UserDto user = this.userService.findById(2);


        // export from object to xml
//        JAXBContext context = JAXBContext.newInstance(user.getClass());
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(user,new File(USERS_FILE_PATH));

        //Import java object from xml
//        JAXBContext context = JAXBContext.newInstance(UserRootDto.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        UserRootDto userRootDto = (UserRootDto) unmarshaller
//                .unmarshal(new File(USERS_FILE_PATH));
//
        this.userService.seedUsers();
//        this.userService.exportUsers();
        System.out.println();
//        UserSeedDto userSeedDto1 = new UserSeedDto("Konstantin","Mitev","Sofia");
//        UserSeedDto userSeedDto2 = new UserSeedDto("Gosho","Petkov","Varna");
//        UserSeedDto userSeedDto3 = new UserSeedDto("Nikolay","Koev","Trun");
//
//        this.userService.save(userSeedDto1);
//        this.userService.save(userSeedDto2);
//        this.userService.save(userSeedDto3);
    }
}

/*
// Save 3 Users to the database
@Override
    public void run(String... args) throws Exception {
        UserSeedDto userSeedDto1 = new UserSeedDto("Konstantin","Mitev","Sofia");
        UserSeedDto userSeedDto2 = new UserSeedDto("Gosho","Petkov","Varna");
        UserSeedDto userSeedDto3 = new UserSeedDto("Nikolay","Koev","Trun");

        this.userService.save(userSeedDto1);
        this.userService.save(userSeedDto2);
        this.userService.save(userSeedDto3);
    }
 */


/*
//Export Single User in xml as
<userDto>
    <first_name>Gosho</first_name>
    <lastName>Petkov</lastName>
    <city>Varna</city>
</userDto>

 @Override
    public void run(String... args) throws Exception {
        UserDto user = this.userService.findById(2);
        JAXBContext context = JAXBContext.newInstance(user.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user,new File(USERS_FILE_PATH));
    }
 */

/*
//Export single UserRootDto with list of UserDto
        List<UserDto>  users = this.userService.findAll();
        UserRootDto user = new UserRootDto();
        user.setUsers(users);

        //export single userDto
//        UserDto user = this.userService.findById(2);


        JAXBContext context = JAXBContext.newInstance(user.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user,new File(USERS_FILE_PATH));
 */

/*
        //Import java object from xml
        JAXBContext context = JAXBContext.newInstance(UserRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UserRootDto userRootDto = (UserRootDto) unmarshaller
                .unmarshal(new File(USERS_FILE_PATH));

 */