package com.example.demo.config;


import com.example.demo.utils.ValidationUtil;
import com.example.demo.utils.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
}
