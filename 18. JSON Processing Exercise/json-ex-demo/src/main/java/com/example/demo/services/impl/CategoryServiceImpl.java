package com.example.demo.services.impl;

import com.example.demo.models.dtos.CategorySeedDto;
import com.example.demo.models.dtos.ex3query3.CategoryWithProductsDto;
import com.example.demo.models.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategory(CategorySeedDto[] categorySeedDtos) {
        // First check if the content that we want to save to the database is not
        // already saved
        // Then if it is not, check if every CategorySeedDto has a valid length of the Name
        // and save it to the database as a Category object
        // In this case for the sake of simplicity if the count is
        // more than 0 we assume that the content was
        // already inserted
        if(this.categoryRepository.count() != 0){
            return;
        }

        Arrays.stream(categorySeedDtos)
                .forEach(categorySeedDto -> {
                    if(this.validationUtil.isValid(categorySeedDto)){
                        Category category = this.modelMapper
                                .map(categorySeedDto, Category.class);

                        //Save the category to the database
                        this.categoryRepository
                                .saveAndFlush(category);
                    }else {
                        // When we are hare, the dto is not valid, and the user should be notified
                        // that the dto will not be mapped to Category and will not be saved to the DB
                        // Ex: if the name is empty, prints the message from CategorySeedDto  "Wrong category name"
                        // From annotation @Length(min = 3, max = 15, message = "Wrong category name")
                        this.validationUtil
                                .violations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<Category> getRandomCategories() {
        Random random = new Random();
        List<Category> resultList = new ArrayList<>();
        int randomCounter = random.nextInt(3) + 1;

        // iterated from 1 to 3 times
        for (int i = 0; i < randomCounter; i++) {
            // Get a number from 1 to repo.cout()
            long randomId = random
                    .nextInt((int) this.categoryRepository.count()) + 1;
            // On each iteration add a random category to the list
            resultList.add(this.categoryRepository.getOne(randomId));
        }
        // The list contains 1 to 3 random Categories
        return resultList;
    }

    //Ex_3_Query3
    @Override
    public List<CategoryWithProductsDto> getAllCategoriesByProductsCount() {
        // Get the values from the query as Object[]
        List<Object[]> allCategoriesByProductsCount = this.categoryRepository
                .getAllCategoriesByProductsCount();

        // Initialize a new ArrayList
        List<CategoryWithProductsDto> categories = new ArrayList<>();
        for (Object[] objects : allCategoriesByProductsCount) {
            CategoryWithProductsDto categoryWithProductsDto = new CategoryWithProductsDto();

            categoryWithProductsDto.setCategory((String) objects[0]);
            Integer count  = Integer.valueOf(String.valueOf(objects[1]));
            categoryWithProductsDto.setProductsCount(count);
            categoryWithProductsDto.setAveragePrice(new BigDecimal(String.valueOf(objects[2])));
            categoryWithProductsDto.setTotalRevenue(new BigDecimal(String.valueOf(objects[3])));

            // Add every new categoryWithProductsDto to the list
            categories.add(categoryWithProductsDto);
        }
        return categories;

    }
}



























