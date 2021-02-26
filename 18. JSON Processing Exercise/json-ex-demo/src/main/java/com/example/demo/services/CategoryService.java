package com.example.demo.services;

import com.example.demo.models.dtos.CategorySeedDto;
import com.example.demo.models.dtos.ex3query3.CategoryWithProductsDto;
import com.example.demo.models.entities.Category;

import java.util.List;

public interface CategoryService {
    void seedCategory(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategories();

    //Ex3_query_3
    List<CategoryWithProductsDto> getAllCategoriesByProductsCount();
}
