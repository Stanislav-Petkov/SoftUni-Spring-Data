package course.springdata.advanced.dao;

import course.springdata.advanced.entity.Ingredient;
import course.springdata.advanced.entity.Label;
import course.springdata.advanced.entity.Shampoo;
import course.springdata.advanced.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findBySizeOrderById(Size size);



    // Ex 2
    List<Shampoo> findBySizeOrLabelOrderByPriceDesc(Size medium, Label label);

    //Ex 3.	Select Shampoos by Price
    List<Shampoo> findAllByPriceGreaterThanEqualOrderByPriceDesc(double price);

    List<Shampoo> findAllByPriceBetween(double minPrice, double maxPrice);

    //6.	Count Shampoos by Price
    List<Shampoo> findAllByPriceLessThan(double price);

    //6.	Count Shampoos by Price
    int countShampooByPriceLessThan(double price);

    //Ex 7.	Select Shampoos by Ingredients
    @Query("SELECT s FROM Shampoo AS s JOIN s.ingredients i  WHERE i.name IN :ingredient_names")
    List<Shampoo> findWithIngredientsIn(
            @Param("ingredient_names") Iterable<String> ingredient_names);
    //Ex 7.	Select Shampoos by Ingredients
//    @Query("SELECT s FROM Shampoo AS s,IN(s.ingredients) i  WHERE i.name IN :ingredient_names")
//    List<Shampoo> findWithIngredientsIn(
//            @Param("ingredient_names") Iterable<String> ingredient_names);

    //8.	Select Shampoos by Ingredients Count
    @Query("SELECT s FROM Shampoo AS s where s.ingredients.size < ?1")
    List<Shampoo> findAllByIngredientsCountLessThan( @Param(value = "1") int count);

    //8.	Select Shampoos by Ingredients Count
    @Query("SELECT s FROM Shampoo AS s where s.ingredients.size < :count")
    List<Shampoo> findAllByCountOfIngredientsLowerThan( @Param("count") int count);

    @Query("SELECT s FROM Shampoo AS s, IN(s.ingredients) i WHERE i = :ingredient")
    List<Shampoo> findByIngredient(Ingredient ingredient);
}
