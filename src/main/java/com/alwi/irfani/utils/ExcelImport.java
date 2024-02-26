package com.alwi.irfani.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alwi.irfani.entity.Product;

public class ExcelImport {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Product> getProductsDataFromExcel(InputStream inputStream){
        List<Product> products = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Products Information");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Product product = new Product();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> product.setId((long) cell.getNumericCellValue());
                        case 1 -> product.setCode(cell.getStringCellValue());
                        case 2 -> product.setName(cell.getStringCellValue());
                        case 3 -> product.setPrice(cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                products.add(product);
            }
            workbook.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        
        return products;
    }
}
