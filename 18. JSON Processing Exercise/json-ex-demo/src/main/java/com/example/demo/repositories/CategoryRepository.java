package com.example.demo.repositories;

import com.example.demo.models.dtos.ex3query3.CategoryWithProductsDto;
import com.example.demo.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Ex_3_Query_3
    @Query(value = "SELECT c.name AS category, COUNT(*) as productsCount, AVG(p.price) as averagePrice,\n" +
            "       SUM(p.price) as totalRevenue\n" +
            "FROM categories AS c\n" +
            "INNER JOIN products_categories AS pc on c.id = pc.categories_id\n" +
            "INNER JOIN products AS p on pc.products_id = p.id\n" +
            "GROUP BY c.id\n" +
            "ORDER BY productsCount DESC", nativeQuery = true)
    List<Object[]> getAllCategoriesByProductsCount();
}
