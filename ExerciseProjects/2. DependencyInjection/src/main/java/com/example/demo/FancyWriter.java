package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("FancyWriter")
public class FancyWriter implements TextWriter{

    public String writeText(String s){
        return s + " fancy Text";
    }
}
