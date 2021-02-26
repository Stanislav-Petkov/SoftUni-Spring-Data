package demos.springdata.restdemo.service;

import demos.springdata.restdemo.model.Post;

import java.util.Collection;

// Spring generated a dynamic proxy when there is an interface
public interface PostService {

    Collection<Post> getPosts();

    // Returns a post with added id as a result
    Post addPost(Post post);

    // Returns a post with added id as a result
    Post updatePost(Post post);

    // Delete post by id, and returns the deleted post
    Post deletePost(Long id);

    // Returns the count of post
    long getPostsCount();

}











