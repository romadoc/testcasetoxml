/**
 * Класс, читающий ТС или его фрагмент, на основании которого необходимо создать XML
 * Текст ТС должен располагаться в ../app/filestorage
 * @author Raman Darashenka
 */
package app.xmlfactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestCaseReader {

    public LinkedHashMap<String, String> getDataForXml(String inputFileName, String taskFileName) throws FileNotFoundException {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/src/main/java/app/filestorage/" + inputFileName;
        LinkedHashMap<String, String> mapTask = new TaskReader().readTask(taskFileName);
        System.out.println("TC reader mapTask " + mapTask); //todo delete
        LinkedHashMap<String, String> mapTestCase = new LinkedHashMap<>();
        LinkedHashMap<String, String> mapForXmlCreator = new LinkedHashMap<>();

        if (mapTask.isEmpty()) {
            System.out.println("Map is empty!");
            return mapForXmlCreator;
        }
        File inputFile = new File(path);
        Scanner scanner = new Scanner(inputFile);
        String line;
        String pattern = "(.*):\\s(.*)";

        Map<String, String> keyMap = new HashMap<>();
        for (String key : mapTask.keySet()) {
            String newKey = key.replaceAll("\\s", "");
            keyMap.put(newKey, key);
        }

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.matches(pattern)) {
                String[] parts = line.split(":\\s");
                String newKey = parts[0].replaceAll("\\s", "");
                String originalKey = keyMap.get(newKey);
                if (originalKey != null) {
                    mapTestCase.put(originalKey, parts[1]);
                }
            }
        }
        scanner.close();

        System.out.println("TCReader mapTestCase " + mapTestCase);
        for (Map.Entry<String, String> entry : mapTask.entrySet()) {
            String key = entry.getKey();
            String value = mapTestCase.get(key);
            if (value != null) {
                mapForXmlCreator.put(entry.getValue(), value);
            }
        }

        System.out.println("mapForXmlCreator " + mapForXmlCreator);


        //System.out.println("TestCaseReader mapForXmlCreator " + mapForXmlCreator); //todo remove
        return mapForXmlCreator;
    }

}
