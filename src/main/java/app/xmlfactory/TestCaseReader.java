/**
 * Класс, читающий ТС или его фрагмент, на основании которого необходимо создать XML
 * Текст ТС должен располагаться в ../app/filestorage
 *
 * @author Raman Darashenka
 */
package app.xmlfactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class TestCaseReader {
    TCFileReader tcFileReader = new TCFileReader();
    public LinkedHashMap<String, String> getDataForXml(String inputFileName, String taskFileName) throws IOException {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/src/main/java/app/filestorage/" + inputFileName;
        File inputFile = tcFileReader.transformFileToTxt(path);
        if (path.endsWith(".xlsx")) {
            path = path.replace(".xlsx", ".txt");
        }
        LinkedHashMap<String, String> mapTask = new TaskReader().readTask(taskFileName);
        LinkedHashMap<String, String> mapTestCase = new LinkedHashMap<>();
        LinkedHashMap<String, String> mapForXmlCreator = new LinkedHashMap<>();

        if (mapTask.isEmpty()) {
            System.out.println("Map is empty!");
            return mapForXmlCreator;
        }


       // File inputFile = tcFileReader.transformFileToTxt(path);
//        if (inputFileName.endsWith(".xlsx")) {
//            inputFileName = inputFileName.replace(".xlsx", ".txt");
//        }
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
        return mapForXmlCreator;
    }

}
