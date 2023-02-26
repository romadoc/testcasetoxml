package app.xmlfactory;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TCFileReader {
    //класс для преобразования в файл txt
    public File transformFileToTxt(String path) throws IOException {
        File inputFile = new File(path);
        String fileName = inputFile.getName();
        if (fileName.endsWith(".txt")) {
            return inputFile;
        } else if (fileName.endsWith(".xls")) {
            Workbook workbook = WorkbookFactory.create(inputFile);
            Sheet sheet = workbook.getSheetAt(0); // получаем первый лист
            StringBuilder sb = new StringBuilder();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    sb.append(cell.getStringCellValue()).append(" ");
                }
                sb.append("\n");
            }
            workbook.close();
            // создаем новый текстовый файл с тем же именем, но расширением .txt
            String txtFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".txt";
            File outputFile = new File(inputFile.getParentFile(), txtFileName);
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(sb.toString().getBytes());
            }
            return outputFile;
        } else {
            return null;
        }
    }

}
