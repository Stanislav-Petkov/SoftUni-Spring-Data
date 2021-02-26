package demos.springdata.restdemo.dao;

import demos.springdata.restdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
