package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // The query is automatically generated from the name of the method
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    //
//    List<Book> getAllBooksByGeorgePowellB OrderByReleaseDateDescTitleAsc
    List<Book> findBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(
            String firstName, String lastName );
}
