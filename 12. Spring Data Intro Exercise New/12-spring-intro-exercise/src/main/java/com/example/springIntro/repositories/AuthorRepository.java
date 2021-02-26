package com.example.springIntro.repositories;

import com.example.springIntro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBooks();

    // Ex 2
    @Query("SELECT a FROM Author AS a " +
            "inner join Book as b ON b.author.id = a.id " +
            "where b.releaseDate < '1990-01-01'")
    List<Author> findAllByBooksReleaseDate();

//    List<Author> findAllB
}
