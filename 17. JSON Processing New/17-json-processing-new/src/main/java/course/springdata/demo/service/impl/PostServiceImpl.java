package course.springdata.demo.service.impl;

import course.springdata.demo.dao.PostRepository;
import course.springdata.demo.entity.Post;
import course.springdata.demo.exception.NonExistingEntityException;
import course.springdata.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new NonExistingEntityException(String.format("Post with ID=%s does not exist.",id)));
    }

    @Transactional
    @Override
    public Post addPost(Post post) {
        post.setId(null);
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(Post post) {
        getPostById(post.getId());
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post deletePost(Long id) {
        Post removed = getPostById(id);
        postRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getPostsCount() {
        return postRepository.count();
    }
}
