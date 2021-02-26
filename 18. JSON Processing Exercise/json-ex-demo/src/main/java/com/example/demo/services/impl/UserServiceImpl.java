package com.example.demo.services.impl;

import com.example.demo.models.dtos.UserSeedDto;
import com.example.demo.models.dtos.ex3query2.SoldProductsDto;
import com.example.demo.models.dtos.ex3query2.UserWithSoldProductListDto;
import com.example.demo.models.dtos.ex3query4.ProductNameAndPriceDto;
import com.example.demo.models.dtos.ex3query4.SoldProductsWithCountDto;
import com.example.demo.models.dtos.ex3query4.UserDetailsDto;
import com.example.demo.models.dtos.ex3query4.UsersWithMoreThan1SoldProductDto;
import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        // First check if the users table does not already have content
        if (this.userRepository.count() != 0) {
            return;
        }

        Arrays.stream(userSeedDtos)
                .forEach(userSeedDto -> {
                    if (this.validationUtil.isValid(userSeedDto)) {
                        User user = this.modelMapper.map(userSeedDto, User.class);

                        this.userRepository.saveAndFlush(user);
                    } else {
                        this.validationUtil
                                .violations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);

                    }
                });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        // nextInt(); returns a random int from 0 to repo.count() not including the last element
        // that is why we add 1 and the random number will be from 1 to the repo.count() including last
        long randomId = random
                .nextInt((int) this.userRepository.count()) + 1;
        return this.userRepository.getOne(randomId);
    }

    // save in a list all UserWithSoldProductListDto
    // save in a list all SoldProductsDto
    // make a stream through them and
    //Ex3_Query_2
    @Override
    public List<UserWithSoldProductListDto> findAllUsersWithAtLeastOneSoldProduct() {
        List<User> users = this.userRepository.findAllUsersWithAtLeastOneSoldProduct();


        System.out.println();
        return this.userRepository.findAllUsersWithAtLeastOneSoldProduct()
                .stream()
                .map(u -> {
                    // Map the outer dto
                    UserWithSoldProductListDto userWithSoldProductListDto = this.modelMapper.map(u,
                            UserWithSoldProductListDto.class);

                    // Get the sold products
                    List<Product> userSoldProducts = new ArrayList<>(u.getSold());

                    // Map the sold product to SoldProductDto
                    List<SoldProductsDto> userSoldProductsDto = userSoldProducts.stream().map(p -> {
                        return this.modelMapper.map(p, SoldProductsDto.class);
                    }).collect(Collectors.toList());

                    // Set the list of SoldProductDto to the userWithSoldProductListDto
                    userWithSoldProductListDto.setSoldProducts(userSoldProductsDto);

                    return userWithSoldProductListDto;
                }).collect(Collectors.toList());
    }

    @Override
    public UsersWithMoreThan1SoldProductDto findAllUsersWithMoreThan1SoldProducts() {
        List<User> allUsers = this.userRepository.findAllUsersWithMoreThan1SoldProducts();

        //We need 1 dto
        UsersWithMoreThan1SoldProductDto usersWithMoreThan1SoldProductDto =
                new UsersWithMoreThan1SoldProductDto();

        usersWithMoreThan1SoldProductDto
                .setUsersCount(allUsers.size());

        Set<UserDetailsDto> userDetailsDtos = new HashSet<>();
        //Set the empty Set


        for (User user : allUsers) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            String firstName = user.getFirstName();
            if (firstName == null) {
                userDetailsDto.setFirstName(null);
            } else {
                userDetailsDto.setFirstName(user.getFirstName());
            }
            userDetailsDto.setLastName(user.getLastName());
            userDetailsDto.setAge(user.getAge());

            // ALL user sold Products
            Set<Product> soldProducts = user.getSold();
            // map the sold products
            Set<SoldProductsWithCountDto> soldProductsWithCount = new HashSet<>();

//            for (Product soldProduct : soldProducts) {
            SoldProductsWithCountDto soldProductWithCount = new SoldProductsWithCountDto();
            soldProductWithCount.setCount(soldProducts.size());

            Set<ProductNameAndPriceDto> productNameAndPriceDtos = new HashSet<>();
            for (Product product : soldProducts) {
                ProductNameAndPriceDto productNameAndPriceDto = new ProductNameAndPriceDto();
                productNameAndPriceDto.setName(product.getName());
                productNameAndPriceDto.setPrice(product.getPrice());
                productNameAndPriceDtos.add(productNameAndPriceDto);
            }

            soldProductWithCount.setProducts(productNameAndPriceDtos);

            soldProductsWithCount.add(soldProductWithCount);

            userDetailsDto.setSoldProducts(soldProductsWithCount);


            userDetailsDtos.add(userDetailsDto);
        }
        usersWithMoreThan1SoldProductDto.setUsers(userDetailsDtos);

        System.out.println();
        return usersWithMoreThan1SoldProductDto;
    }
}
