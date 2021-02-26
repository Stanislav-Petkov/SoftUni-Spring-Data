package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Autowired
    @Qualifier("RegularWriter")
    TextWriter regular;

    @Autowired
    @Qualifier("FancyWriter")
    TextWriter fancy;


    // Example 3 without constructor, @Autowired makes DI on the setters


    // Example 2 Now we inject the TextWriter in the constructor without instantiating it
//    public HelloWorld(TextWriter text){
//        this.text = text;
//    }


    // Example without dependency injection
    // HelloWorld in this way is tightly coupled to FancyWriter class
    // If we want to test it, we can test it only with class FancyWriter
    // Which now does not have a @Component, also TextWriter does not have it
//    public HelloWorld() {
//        text = new FancyWriter();
//    }

    @RequestMapping("/")
    public String helloWorld(){
//        return regular.writeText("hi "); // makes the text toUpper
        return fancy.writeText("hi ");
    }
}
