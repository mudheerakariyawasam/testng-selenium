package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    private static final String FILE_NAME = "src/test/java/resources/testdata.xlsx";

    // Method to read data for the search queries
    public static List<Object[]> getSearchQueryData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        // Open the Excel file
        FileInputStream file = null;
        Workbook workbook = null;
        try {
            file = new FileInputStream(FILE_NAME);
            workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String searchQuery = row.getCell(0).getStringCellValue();
                data.add(new Object[]{searchQuery}); // Add search query as a parameter
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (file != null) {
                file.close();
            }
        }
        return data;
    }
}
