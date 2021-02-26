package demos.springdata.restdemo.service.impl;

import demos.springdata.restdemo.dao.PostRepository;
import demos.springdata.restdemo.dao.UserRepository;
import demos.springdata.restdemo.exception.InvalidEntityException;
import demos.springdata.restdemo.model.Post;
import demos.springdata.restdemo.model.User;
import demos.springdata.restdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Collection<Post> getPosts() {
        return postRepo.findAll();
    }

    // Cascading serialization will cause an issue when the post is persisted to the author,
    // because the author should already be created and logged to make a post
    @Override
    public Post addPost(Post post) {
        Long authorId;
        // If we know the author
        if(post.getAuthor() != null && post.getAuthor().getId() != null){
            // if the author of the post was already submitted
            authorId = post.getAuthor().getId();
        }else {
            // get the value of the new post
            authorId = post.getAuthorId();
        }
        // If a post with not existing author is received, InvalidEntityException is thrown
        // and it is mapped to http 400
        if(authorId != null){
            // If the the authorId is valid, we receive the author
            // .orElseThrow is when the the authorId is not null, but there is no such author and an Exception is thrown
            User author = userRepo.findById(authorId)
                    .orElseThrow(() -> new InvalidEntityException("Author with ID = "
                            + authorId + " does not exist."));

            // Set the found author to the post
            post.setAuthor(author);
            //post.getAuthor();
        }

        // If there is a creation date already
        if(post.getCreated() == null){
            // set the current date
            post.setCreated(new Date());
        }
        // When the post is created, the 2 dates should be equal
        post.setModified(post.getCreated());
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post deletePost(Long id) {
        return null;
    }

    @Override
    public long getPostsCount() {
        return 0;
    }
}
