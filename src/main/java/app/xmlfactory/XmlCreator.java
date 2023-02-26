/**
 * Класс, создающий результат в виде XML файла. Созданный файл будет располагаться по пути:
 * ../src/app/filestorage
 *
 * @author Raman Darashenka
 */
package app.xmlfactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlCreator {
    TestCaseReader testCaseReader = new TestCaseReader();


    public void createXML(String inputFileName, String taskFileName, String outputFileName) throws IOException, XMLStreamException {
        String userDir = System.getProperty("user.dir");
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(userDir +
                "/src/main/java/app/filestorage/" + outputFileName + ".xml")); //результирующий XML- файл

        LinkedHashMap<String, String> dataMap = testCaseReader.getDataForXml(inputFileName, taskFileName);

        writer.writeStartDocument();
        writer.writeCharacters("\n"); // добавление переноса строки после заголовка документа
        writer.writeStartElement("TypeYourClassName_Here");
        writer.writeCharacters("\n\t\t"); // добавление отступа после корневого тега

        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            writer.writeStartElement(entry.getKey());
            writer.writeCharacters(entry.getValue());
            writer.writeEndElement();
            writer.writeCharacters("\n\t\t"); // добавление отступа перед следующим тегом
        }
        writer.writeCharacters("\n"); // добавление отступа перед тегом корня
        writer.writeEndDocument();

        writer.flush();
        writer.close();

        System.out.println("XML файл успешно создан.");
    }

}
