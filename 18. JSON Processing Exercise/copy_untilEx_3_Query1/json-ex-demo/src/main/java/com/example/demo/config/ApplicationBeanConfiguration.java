package com.example.demo.config;

import com.example.demo.utils.ValidationUtil;
import com.example.demo.utils.ValidationUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    // We need a @Bean in order to inject the ModelMapper into the CategoryServiceImpl
    // modelMapper field
    // ModelMapper is used to map CategorySeedDto to a Category
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // We need a @Bean in order to inject the ValidationUtil into the CategoryServiceImpl
    // validationUtil field
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

}




















