package com.example.demo.utils;

import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XMLParser {
    // object is which object to export and path is to which path/xml file
    <O> void exportToXML(O object, String path) throws JAXBException;

    // path is from where to read the path/xml file
    <O> O importFromXml(Class<O> klass, String path) throws JAXBException, FileNotFoundException;
}
