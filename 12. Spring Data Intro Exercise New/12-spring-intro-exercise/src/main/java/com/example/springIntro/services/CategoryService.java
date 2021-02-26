package com.example.springIntro.services;

import com.example.springIntro.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;
    Category getCategoryById(Long id);
}
