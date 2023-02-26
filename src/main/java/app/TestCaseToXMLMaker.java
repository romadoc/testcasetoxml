/**
 * Класс-стартер приложения
 * При необходимости внесите изменение в параметры методов этого класса
 * @author Raman Darashenka
 */
package app;

import app.xmlfactory.XmlCreator;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class TestCaseToXMLMaker {
    public static void main(String[] args) throws IOException, XMLStreamException {
        String inputTestCaseFile = "input.txt";
        String inputTaskFile = "task.txt";
        String xmlFileNime = "created_data";
        XmlCreator xmlCreator = new XmlCreator();
        xmlCreator.createXML(inputTestCaseFile, inputTaskFile, xmlFileNime);
    }
}
