package demos.springdata.advanced.dao;

import demos.springdata.advanced.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    //Problem 4 Create a method that selects all ingredients, which name starts with given letters.
    List<Ingredient> findByNameStartingWith(String startingLetters);

    // Problem 5 Create a method that selects all ingredients, which are contained in a given list.
    // Sort the result ascending by price.
    List<Ingredient> findByNameInOrderByPriceAsc(Collection<String> ingredientsNames);

    Ingredient findByName(String name);


    //for problem 9 the id of the ingredient which will be deleted first should be replaced in shampoos_ingredients
    // Cannot delete or update a parent row: a foreign key constraint fails (`shampoos`.`shampoos_ingredients`, CONSTRAINT `FK

    @Modifying
    @Transactional
    @Query(value = "update shampoos_ingredients set ingredient_id = 15 where ingredient_id = 1", nativeQuery = true)
    int updateIngredientIdInShampoos_Ingredients();

    // problem 9 delete all ingredients by a given name
    @Modifying
    @Transactional
    void deleteAllByNameEquals(String name);

    //problem 10
    @Modifying
    @Transactional
    @Query(value = "UPDATE Ingredient i SET i.price = i.price * 1.1")
    int updateAllIngredientsPriceWith10Percent();

    // problem 11 Create a method that increases the price of all ingredients by 10%. Use named query.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.modifying-queries 5.3.8
    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1 WHERE i.name in :names")
    int updatePriceIngredientsInListBy10Percent
    (@Param("names") Iterable<String>names);

}
