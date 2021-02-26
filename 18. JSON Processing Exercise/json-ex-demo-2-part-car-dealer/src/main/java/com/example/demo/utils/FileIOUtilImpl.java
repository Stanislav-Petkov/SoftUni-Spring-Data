package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileIOUtilImpl implements FileIOUtil{
    @Override
    public String readFileContent(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .filter(x -> !x.isBlank())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public void write(String content, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Set<String> collection = Collections.singleton(content);
        Charset utf8 = StandardCharsets.UTF_8;
        Files.write(path, collection, utf8);
    }
}

















