package demos.springdata.advanced.dao;

import demos.springdata.advanced.entity.Ingredient;
import demos.springdata.advanced.entity.Label;
import demos.springdata.advanced.entity.Shampoo;
import demos.springdata.advanced.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    // Problem 1 Select shampoos by size
    List<Shampoo> findBySize(Size size);

    //Problem 2 selects all shampoos with a given size or label id. Sort the result ascending by price.
    List<Shampoo> findBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    // Problem 3 selects all shampoos, which price is higher than a given price.
    // Sort the result descending by price.
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(double price);

    //Problem 6 Create a method that counts all shampoos with price lower than a given price.
    int countAllByPriceLessThan(double price);

    // Problem 7 Create a method that selects all shampoos with ingredients included in a given list.
    // @Query is from spring data
    // @Param("ingredients") annotaion is for the Iterable(List) Iterable<Ingredient> ingredients
    // also it is the same as the name of the parameter in the query :ingredients
    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i IN :ingredients")
    List<Shampoo> findWithIngredientsInList(@Param("ingredients") Iterable<Ingredient> ingredients);

    // Problem 8 Create a method that selects all shampoos with ingredients less than a given number.
    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :ingredientsCount")
    List<Shampoo> findByIngredientsCountLessThan(@Param("ingredientsCount") int ingredientsCount);
}
