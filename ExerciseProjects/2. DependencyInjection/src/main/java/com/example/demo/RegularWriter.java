package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("RegularWriter")
public class RegularWriter implements TextWriter{
    @Override
    public String writeText(String s) {
        return s.toUpperCase();
    }
}
