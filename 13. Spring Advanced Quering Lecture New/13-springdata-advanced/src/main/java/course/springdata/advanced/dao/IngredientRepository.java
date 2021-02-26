package course.springdata.advanced.dao;

import course.springdata.advanced.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameIn(Iterable<String> names);

    int deleteAllByName(String name);

    Optional<Ingredient> findByName(String name);

    //Ex 10 and 11
    @Transactional
    @Modifying
    @Query("UPDATE Ingredient AS i SET i.price = i.price * (1.0 + :percentage) " +
            " WHERE i.name IN :names ")
    int updatePriceOfIngredientsInList(@Param("names") Iterable<String> ingredient_names,
                                       @Param("percentage") double percentage);
}
