package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <O> O convertFromFile(String filePath,Class<O> objectClass) throws JAXBException;

    // The object that will be exported, its class, FILE_PATH
//    <O> void exportXml(O object, Class<O> objectClass, String filePath) throws JAXBException;
}
