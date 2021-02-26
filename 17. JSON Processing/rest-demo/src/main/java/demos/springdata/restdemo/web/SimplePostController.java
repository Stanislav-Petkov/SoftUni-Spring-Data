package demos.springdata.restdemo.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import demos.springdata.restdemo.model.Post;
import demos.springdata.restdemo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;

// The @RestController makes SimplePostController a bean

// The class will be a MVC Controller and will
// directly return the body as a response
// Returns JSON directly

// api is the prefix for REST API
// simple is the endpoint
@RestController
@RequestMapping("/api/simple")
@Slf4j
public class SimplePostController {
    // inject the lower layer
    @Autowired
    private PostService postService;

    private Gson gson;

    // when the bean is initialized the method is called and the gson parser is initialized

    @PostConstruct
    private void init() {
        // We use                 .excludeFieldsWithoutExposeAnnotation()
        // otherwise it tries to introspect the code and a cycle is created between the beans
        // We set to be inspected only what we want to be serialized based on the @Expose annotation
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    // if the method should return a List or Collection jackson should be used and not gson

    // our collection is mapped to /api/simple on a get method
    // the method returns media type application json / a string which the gson has serialized
    //  @GetMapping is a combination of request mapping with get method
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPosts() {
        // receives all posts and returns them as a json string parsed with gson parser
        // when there is a get request on http://localhost:8080/api/simple
        return gson.toJson(postService.getPosts());
    }

    // the post is to the same url "/api/simple"
    // returns location header with status 201 CREATED
    // otherwise the client cannot know what id it has in the database
    // location header gives the id
    // The location header should have the new URL of the resource

    // The method receives the body as a String

    // ResponseEntity<String> it returns it so that there will be a status code,
    // ResponseEntity contains the body, header, status code
    // ResponseEntity<String> has String , because created is serialized to JSON string
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPost(@RequestBody String body) {
        log.info("Body received: {}", body);

        // deserialize the body from json to object with gson
        //https://sites.google.com/site/gson/gson-user-guide#TOC-Serializing-and-Deserializing-Generic-Types
        //Make a post object from the JSON part
        Post post = gson.fromJson(body, Post.class);
        log.info("Post deserialized: {}", post);

        //Then we should return the post after we Serialize it , but this time with an id
        // created has the post with filled id
        Post created = postService.addPost(post);

        // in order to build a ResponseEntity first build the url
        // URI of the newly created user, will be created and returned in the location header
        // MvcUriComponentsBuilder by the name of the method returns the mapping
        URI uri = MvcUriComponentsBuilder
                .fromMethodName(SimplePostController.class, "addPost", post)
                .pathSegment("{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(gson.toJson(created));
    }
}
/*
In oder to test GET and POST use Insomnia
POST http://localhost:8080/api/simple
JSON
{
	"title": "New Post from 05 Sept",
	"content": "New Post from 05 Sept Content..."
}

GET http://localhost:8080/api/simple
 */




























