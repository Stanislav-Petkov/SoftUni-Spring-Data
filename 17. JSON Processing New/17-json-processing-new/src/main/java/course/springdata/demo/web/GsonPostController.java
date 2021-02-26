package course.springdata.demo.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import course.springdata.demo.entity.Post;
import course.springdata.demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/gson/posts")
@Slf4j
public class GsonPostController {
    @Autowired
    private PostService postService;

    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    //Test from Insomnia with GET http://localhost:8080/api/gson/posts
    // produces says to return Content-Type	application/json which is the
    // correct media type
    @GetMapping(produces = "application/json")
    public String getPosts() {
        return gson.toJson(postService.getAllPosts());
    }

    //GET http://localhost:8080/api/gson/posts/1
    @GetMapping(path = "/{id}", produces = "application/json")
    public String getPosts(@PathVariable("id") Long id){
        return gson.toJson(postService.getPostById(id));
    }

    //Test from Insomnia with POST http://localhost:8080/api/gson/posts
    //  {
    //    "title": "Welcome to GSON 101",
    //    "content": "Developing apss with GSON ...",
    //    "imageUrl": "https://www.publicdomainpictures.net/pictures/320000/velka/rosa-klee-blute-blume.jpg",
    //    "author": "Stanislav Petkov"
    //  }
    //Post method with 201 Created and URI builder for Location
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> addPost(@RequestBody String body){
        log.info("Body received: {}",body);
        // Parse from web to post
        Post post = gson.fromJson(body, Post.class);
        log.info("Post deserialized: {}",post);
        // Save in db and get with id
        Post created = postService.addPost(post);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(created.getId().toString())
                .toUri()
        ).body(gson.toJson(created));
    }
}

















