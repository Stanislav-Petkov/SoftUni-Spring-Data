package com.example.demo.services.impl;

import com.example.demo.models.dtos.ProductInRangeDto;
import com.example.demo.models.dtos.ProductSeedDto;
import com.example.demo.models.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// When there is an entity Product with a nested entity(Category,User).
// With @Transactional the nested
// entities will be saved in the database first so that there is no error
// when we try to save the outer entity

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public ProductServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper,
                              ProductRepository productRepository,
                              CategoryService categoryService, UserService userService) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        if (this.productRepository.count() != 0) {
            return;
        }

        Arrays.stream(productSeedDtos).forEach(productSeedDto -> {
            if (this.validationUtil.isValid(productSeedDto)) {
                Product product = this.modelMapper.map(productSeedDto, Product.class);
                //There is always a seller
                product.setSeller(this.userService.getRandomUser());

                Random random = new Random();
                // Returns 0 or 1
                int randomNum = random.nextInt(2);
                if (randomNum == 1) {
                    // Some of the products will have a Buyer
                    product.setBuyer(this.userService.getRandomUser());
                }

//                List<Category> categories = this.categoryService.getRandomCategories();
//                Set<Category> categorySet = new HashSet<>(categories);
//                product.setCategories(categorySet);
                product.setCategories(new HashSet<>(this.categoryService.getRandomCategories()));

                this.productRepository.saveAndFlush(product);
            } else {
                this.validationUtil.violations(productSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public List<ProductInRangeDto> getAllProductsInRangeOrderByPrice() {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(
                        BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream()
                .map(p -> {
                    // The seller is missing at this point
                    ProductInRangeDto productInRangeDto = this.modelMapper.map(
                            p, ProductInRangeDto.class);

                    // Set the seller names to the dto String seller field
                    productInRangeDto.setSeller(String.format("%s %s",
                            p.getSeller().getFirstName(),p.getSeller().getLastName()));

                    return productInRangeDto;
                }).collect(Collectors.toList());
    }
}
