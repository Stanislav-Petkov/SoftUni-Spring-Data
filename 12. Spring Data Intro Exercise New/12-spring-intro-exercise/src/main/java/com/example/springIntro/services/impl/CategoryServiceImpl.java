package com.example.springIntro.services.impl;

import com.example.springIntro.constants.GlobalConstants;
import com.example.springIntro.entities.Category;
import com.example.springIntro.repositories.CategoryRepository;
import com.example.springIntro.services.CategoryService;
import com.example.springIntro.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.example.springIntro.constants.GlobalConstants.CATEGORIES_FILE_PATH;

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
        if(this.categoryRepository.count() != 0){
            return;
        }
        String[] fileContent = this.fileUtil
                .readFileContent(CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    Category category = new Category(r);
                    this.categoryRepository.saveAndFlush(category);
                });

    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }
}
















