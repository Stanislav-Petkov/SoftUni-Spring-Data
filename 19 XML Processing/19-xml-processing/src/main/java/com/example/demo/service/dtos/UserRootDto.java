package com.example.demo.service.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// The root element is in plural because it holds many single UserDto
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDto {

    /*
    The name "user" in
    @XmlRootElement(name = "user")
        public class UserDto { }
    must be the same as the user on this list field
    @XmlElement(name = "user") // single user
    private List<UserDto> users;
     */

    // The element is user, because it corresponds to a single user dto object
    // user will be the xml tag for a single UserDto object in the users.xml
    @XmlElement(name = "user") // user is for a single user dto name object in the list
    private List<UserDto> users;

    public UserRootDto() {
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
