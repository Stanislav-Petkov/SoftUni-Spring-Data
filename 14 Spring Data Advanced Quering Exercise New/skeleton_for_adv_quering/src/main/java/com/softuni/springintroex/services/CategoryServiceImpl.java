package com.softuni.springintroex.services;

import com.softuni.springintroex.domain.entities.Category;
import com.softuni.springintroex.domain.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.softuni.springintroex.constants.GlobalConstants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategoriesInDb() throws IOException {
        // read categories.txt
        String[] lines = fileUtil.readFileContent(CATEGORIES_FILE_PATH);


        for (String line : lines) {
            // new Category()
            Category category = new Category(line);
            //save in db
            categoryRepository.saveAndFlush(category);
        }


    }
}
