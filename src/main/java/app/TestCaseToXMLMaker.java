/**
 * Класс-стартер приложения
 * При необходимости внесите изменение в параметры методов этого класса
 *
 * @author Raman Darashenka
 */
package app;

import app.xmlfactory.XmlCreator;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class TestCaseToXMLMaker {
    public static void main(String[] args) throws IOException, XMLStreamException {
        String inputTestCaseFile = "exel.xlsx";
        String inputTaskFile = "task.txt";
        String xmlFileName = "created_data";
        XmlCreator xmlCreator = new XmlCreator();
        xmlCreator.createXML(inputTestCaseFile, inputTaskFile, xmlFileName);
    }
}
