package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public interface TextWriter {
    public abstract String writeText(String s);
}
