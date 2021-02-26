package com.example.demo.config;

import com.example.demo.utils.ValidationUtil;
import com.example.demo.utils.ValidationUtilImpl;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public Gson gson() {
//        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .create();

        //In order to deserialize Customer birthdate 1993-11-20T00:00:00
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeGsonDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeGsonSerializer())
                .serializeNulls()
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
