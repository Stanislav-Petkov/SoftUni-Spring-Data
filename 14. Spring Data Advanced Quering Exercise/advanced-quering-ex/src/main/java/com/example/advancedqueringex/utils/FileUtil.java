package com.example.advancedqueringex.utils;

import java.io.IOException;
import java.util.List;

// With Component, Spring makes a FileUtilImpl instance of the interface FileUtil
// When Spring app is started the FileUtil is connected to Spring
// and we can inject it to services through the interface
// Spring injects FileUtil to the service through a constructor
public interface FileUtil {
    String[] readFileContent(String filePath) throws IOException;
    List<String> readFileData(String path) throws IOException;
}
