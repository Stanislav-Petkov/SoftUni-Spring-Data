package com.example.advancedqueringex.services.impl;


import com.example.advancedqueringex.entities.Category;
import com.example.advancedqueringex.repositories.CategoryRepository;
import com.example.advancedqueringex.services.CategoryService;
import com.example.advancedqueringex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.example.advancedqueringex.constants.GlobalConstants.CATEGORIES_FILE_PATH;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedCategories() throws IOException {
        // check if the database is not empty so that the data is not added again

        if(this.categoryRepository.count() != 0){
            return;
        }

        String[] fileContent = this.fileUtil
                .readFileContent(CATEGORIES_FILE_PATH);

        // add the fileContent to the database

        Arrays.stream(fileContent)
                .forEach(r -> { // for each row make a new category, where r is the name of the category
                    Category category = new Category(r);
                    this.categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }

    public Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();
        // There are 3 categories , we get a random number from 1 to 3
        Random random = new Random();
        int bound = random.nextInt(3) + 1;
        //random.nextInt(3) returns from 0 to 2
        //random.nextInt(3) + 1 returns from 1 to 3

        // From 1 to 3 times will add a random category fro 1 to 8, we have 8 categories
        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.getCategoryById((long) categoryId));
        }
        return result;
    }
}
