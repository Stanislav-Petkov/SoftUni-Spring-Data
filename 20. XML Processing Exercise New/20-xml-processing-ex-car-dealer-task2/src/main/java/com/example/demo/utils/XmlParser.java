package com.example.demo.utils;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException;

    // The object that will be exported, its class, FILE_PATH
    <O> void exportXml(O object, Class<O> objectClass, String filePath) throws JAXBException;
}
