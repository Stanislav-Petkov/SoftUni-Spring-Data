package com.example.advancedqueringex.repositories;

import com.example.advancedqueringex.entities.AgeRestriction;
import com.example.advancedqueringex.entities.Book;
import com.example.advancedqueringex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Problem 1 Write a program that prints the titles of all books, for which the age restriction
    // matches the given input (minor, teen or adult). Ignore casing of the input.
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    //Problem 2 Write a program that prints the titles of the golden edition books, which have less than 5000 copies.
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    //Problem 3 Write a program that prints the titles and prices of books
    // with price lower than 5 and higher than 40.
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThanPrice
            , BigDecimal higherThanPrice);

    //Problem 4 Write a program that prints the titles of all books that are NOT released in a given year.
    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDateBefore
            , LocalDate releaseDateAfter);

    //Problem 5  Write a program that prints the title, the edition type and the price of books,
    // which are released before a given date. The date will be in the format dd-MM-yyyy.
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    //Problem 7 Write a program that prints the titles of books,
    // which contain a given string (regardless of the casing).
    //List<Book> findAllByTitleContainsIgnoreCase(String str);
    List<Book> findAllByTitleContains(String str);

    //Problem 8
    List<Book> findBooksByAuthor_LastNameStartingWith(String startingString);

    //Problem 8
    @Query(value = "SELECT b FROM Book AS b INNER JOIN b.author as t WHERE t.lastName LIKE ?1%")
    List<Book> findBooksByAuthor_LastNameStartingWithJPQL(String startString);

    //Problem 9
    @Query(value = "SELECT count(b) FROM Book AS b WHERE length(b.title) > ?1")
    int findAllByTitleIsLongerThan(int length);

    //Problem 10 Solution 2
    @Query("SELECT sum(b.copies) FROM Book AS b WHERE " +
            "concat(b.author.firstName, ' ', b.author.lastName) = :name ")
    int findAllCopiesByAuthor(@Param("name") String fullName);

    //Problem 11 Write a program that prints information (title, edition type,
    // age restriction and price) for a book by given title. When retrieving the book information select
    // only those fields and do NOT include any other information in the returned result.
    @Query(value = "SELECT b.title,b.edition_type,b.age_restriction,b.price " +
            "FROM books b " +
            "WHERE b.title = ?1 ", nativeQuery = true)
    String findTitleEditionTypeAgeRestrictionPriceByTitle(String title);

    //Problem 11 with jpql
    @Query(value = "SELECT b.title,b.editionType,b.ageRestriction,b.price FROM Book as b WHERE b.title = ?1")
    String findTitleEditionTypeAgeRestrictionPriceByTitleJPQL(String title);


    //Problem 12 part 1/2
    @Query(value = "SELECT COUNT(b) FROM Book AS b WHERE b.releaseDate > ?1")
    int findAllByReleaseDateAfter(LocalDate localDate);

    //Problem 12 part 2/2
    @Transactional
    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies + ?1 ") //WHERE b.releaseDate > ?2
    void updateBookCopiesAfterADate(int value);

    //Problem 12 Solution 2
    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateAllBooksAfterGivenDate(@Param("date") LocalDate date, @Param("copies") int copies);

    //Problem 14
    @Query(value = "CALL get_number_of_books_by_author_first_and_last_name(?1,?2);", nativeQuery = true)
    int findNumberOfBooksByAuthorFirstAndLastName(String firstName, String lastName);

}


//    // The query is automatically generated from the name of the method
//    List<Book> findAllByReleaseDateAfter(LocalDate localDate);
//
//    //
////    List<Book> getAllBooksByGeorgePowellB OrderByReleaseDateDescTitleAsc
//    List<Book> findBooksByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(
//            String firstName, String lastName);
