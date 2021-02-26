package com.example.advancedqueringex.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public List<String> readFileData(String path) throws IOException {
        return Files.readAllLines(Paths.get(path))
                .stream()
                .filter(x -> !x.isEmpty()) // remove empty lines
                .collect(Collectors.toList());
    }
    @Override
    public String[] readFileContent(String filePath) throws IOException {

        File file = new File(filePath);

        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(file));

        Set<String> result = new LinkedHashSet<>();
        String line;

        while ((line = bufferedReader.readLine()) != null){
            if(!"".equals(line)){
                result.add(line);
            }
        }

        return result.toArray(String[]::new);
    }
}
