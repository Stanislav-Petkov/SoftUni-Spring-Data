package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    // When we go to the / we will see the Hello world text
    @RequestMapping("/")
    public String hello(){
        return "Hello World from method";
    }
}
