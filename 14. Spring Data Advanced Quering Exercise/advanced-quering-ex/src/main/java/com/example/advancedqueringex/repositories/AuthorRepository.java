package com.example.advancedqueringex.repositories;

import com.example.advancedqueringex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Ex. 2
    @Query(value = "SELECT DISTINCT a FROM Book b INNER JOIN b.author a " +
            "WHERE b.releaseDate < '1990-01-01'")
    List<Author> findAllAuthorsByBookWithRelease();


    // The query should be written in the repository
    // The service uses the repository to connect to the database.

    //3.	Get all authors, ordered by the number of their books (descending).
    // Print their first name, last name and book count.
    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBook();

    //Problem 6 Write a program that prints
    // the names of those authors, whose first name ends with a given string.
    List<Author> findAllByFirstNameEndsWith(String endsWith);

    //Problem 10  Write a program that prints the total number of book copies by author.
    // Order the results descending by total book copies.
    @Query(value = "SELECT SUM(b.copies) " +
            "FROM authors AS a " +
            "INNER JOIN books b on a.id = b.author_id " +
            "WHERE a.first_name = ?1 AND a.last_name = ?2 "
            , nativeQuery = true)
    int findSumOfCopiesByAuthorNames(String firstName, String lastName);

}
