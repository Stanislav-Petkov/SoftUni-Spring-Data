package com.softuni.springintroex.utils;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

// With Component, Spring makes a FileUtilImpl instance of the interface FileUtil
// When Spring app is started the FileUtil is connected to Spring
// and we can inject it to services through the interface
// Spring injects FileUtil to the service through a constructor
public interface FileUtil {
    String[] readFileContent(String filePath) throws IOException;
}
