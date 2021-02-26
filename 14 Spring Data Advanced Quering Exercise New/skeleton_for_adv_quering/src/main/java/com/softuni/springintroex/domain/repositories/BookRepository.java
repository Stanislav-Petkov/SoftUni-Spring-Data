package com.softuni.springintroex.domain.repositories;

import com.softuni.springintroex.domain.entities.AgeRestriction;
import com.softuni.springintroex.domain.entities.Book;
import com.softuni.springintroex.domain.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //Ex 1
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    //Ex 2
    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    //Ex 3
    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(
            BigDecimal maxPrice,BigDecimal minPrice);
    //Ex 4
    @Query("SELECT b FROM Book AS b WHERE substring(b.releaseDate,0,4) not like :year ")
    Set<Book> findAllByReleaseDateNotInYear(String year);

    //Ex5
    Set<Book> findAllByReleaseDateIsLessThan(LocalDate date);

    //Ex 7
    Set<Book> findAllByTitleContaining(String containingString);

    //Ex 8
    @Query("SELECT b FROM Book AS b WHERE b.author.lastName LIKE :startingString%")
    Set<Book> findAllByAuthorLastNameStartWith(String startingString);

    //Ex 8
    Set<Book> findAllByAuthorLastNameStartingWith(String startingString);

    //Ex 9
    @Query("SELECT count(b) FROM Book AS b WHERE length(b.title) > :minLength")
    int findAllByTitleIsGreaterThan(int minLength);

    Book findByTitle(String title);

    //ex 12
    @Transactional
    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copiesToAdd WHERE b.releaseDate > :newReleaseDate")
    int updateCopies(int copiesToAdd, LocalDate newReleaseDate);
}
