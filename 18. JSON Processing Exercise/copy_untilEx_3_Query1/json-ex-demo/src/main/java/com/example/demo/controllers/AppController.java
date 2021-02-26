package com.example.demo.controllers;

import com.example.demo.models.dtos.CategorySeedDto;
import com.example.demo.models.dtos.ProductInRangeDto;
import com.example.demo.models.dtos.ProductSeedDto;
import com.example.demo.models.dtos.UserSeedDto;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import com.example.demo.utils.FileIOUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.demo.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    //https://www.baeldung.com/spring-autowire#:~:text=Enabling%20%40Autowired%20Annotations,is%20called%20Spring%20bean%20autowiring.
    @Autowired
    public AppController(Gson gson, CategoryService categoryService,
                         UserService userService, ProductService productService,
                         FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }


    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
        this.seedProducts();

        //Ex 3 Query 1 – Products in Range
        this.writeProductInRange();
    }

    private void writeProductInRange() throws IOException {
        List<ProductInRangeDto> dtos = this.productService
                .getAllProductsInRangeOrderByPrice();

        // Get all dtos in json format
        String json = this.gson.toJson(dtos);

        // write the the dtos in json format to a file
        this.fileIOUtil.write(json,EX_1_OUTPUT);
        System.out.println();
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson
                .fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);

        this.productService.seedProducts(dtos);
    }








    public void seedUsers() throws FileNotFoundException{
        // Get the dtos from the file
        UserSeedDto[] userSeedDto = this.gson
                .fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);
        this.userService.seedUsers(userSeedDto);
    }

    public void seedCategories() throws FileNotFoundException {

        // Get an array of the dtos generated from the JSON from the file categories.json
        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(CATEGORIES_FILE_PATH)
                ,CategorySeedDto[].class);

        this.categoryService.seedCategory(dtos);
    }
}
