package demos.springdata.restdemo.init;

import demos.springdata.restdemo.model.Post;
import demos.springdata.restdemo.model.User;
import demos.springdata.restdemo.service.PostService;
import demos.springdata.restdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// @Slf4j adds a logger
@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private static final List<Post> SAMPLE_POSTS = List.of(
            new Post("Welcome to Spring Data", "Developing data access objects with Spring is easy..."),
            new Post("Reactive Spring Data", "Check R2DBC for reactive JDBC API.."),
            new Post("Now in Spring 5", "Webflux provides reactive and non-blocking web service implementation...")
    );

    private static final List<User> SAMPLE_USERS = List.of(
            new User("Default", "Admin", "admin", "admin"),
            new User("Ivan", "Petrov", "ivan", "ivan")
    );
    @Override
    public void run(String... args) throws Exception {
        // add the users to the database
        SAMPLE_USERS.forEach(user -> userService.addUser(user));
        log.info("Created Users: {}", userService.getUsers());

        SAMPLE_POSTS.forEach(post -> {
            // Set the admin to be the author of the post
            post.setAuthor(userService.getUserById(1L));
            postService.addPost(post);
        });
        log.info("Created Posts: {}", postService.getPosts());
    }
}






















