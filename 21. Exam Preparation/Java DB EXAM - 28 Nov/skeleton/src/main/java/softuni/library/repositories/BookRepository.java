package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.library.models.entities.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findById(Integer id);

    //Book get(@Param("name") String name)
    //Optional<Book> findByNameAndId(String name, Integer id);
}
