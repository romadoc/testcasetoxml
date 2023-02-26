/**
 * Класс, считывающий подготовленный на основании ТС файл, состоящий из пар:
 * "Название поля в тест кейсе": "Название поля в вашем классе"
 * разделителем является двоеточие + пробел
 * файл должен располагаться в ../src/app/filestorage иметь формат .txt
 *
 * @author Raman Darashenka
 */
package app.xmlfactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class TaskReader {
    public LinkedHashMap<String, String> readTask(String taskFileName) throws FileNotFoundException {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/src/main/java/app/filestorage/" + taskFileName; //путь к файлу, где лежит файл Название в ТС: название поля класса

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        File inputFile = new File(path);
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] parts = line.split(": "); // при необходимости измените разделитель
            String key = parts[0].trim();
            String value = parts[1].trim();
            map.put(key, value);

        }

        scanner.close();
        return map;
    }

}
