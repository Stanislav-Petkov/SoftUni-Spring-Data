package demos.springdata.restdemo.service;

import demos.springdata.restdemo.model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getUsers();

    // Returns a User with added id as a result
    User addUser(User user);

    // We return User or Exception and not Optional so that in
    // the web layer we map the exception to a status code
    User getUserById(Long id);

    User getUserByUsername(String username);

    // Returns a User with added id as a result
    User updateUser(User user);

    // Delete User by id, and returns the deleted User
    User deleteUser(Long id);

    // Returns the count of User
    long getUsersCount();


}
